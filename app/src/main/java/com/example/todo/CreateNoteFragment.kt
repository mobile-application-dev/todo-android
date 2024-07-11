package com.example.todo

import android.content.pm.PackageManager
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.app.ActivityCompat
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import com.example.todo.databinding.FragmentCreateNoteBinding
import com.example.todo.view.NoteViewModel
import com.google.android.gms.common.GooglePlayServicesUtil
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices

class CreateNoteFragment : Fragment() {
    private lateinit var binding: FragmentCreateNoteBinding
    private lateinit var viewModel: NoteViewModel
    private val FINE_LOCATION = android.Manifest.permission.ACCESS_FINE_LOCATION
    private val COARSE_LOCATION = android.Manifest.permission.ACCESS_COARSE_LOCATION
    private lateinit var locationProvider: FusedLocationProviderClient
    private  val locationPermissionRequest = requestPermission()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_create_note, container, false)
        locationProvider = LocationServices.getFusedLocationProviderClient(requireContext())
        if(isGooglePlayServicesAvailable() != 0) {
            show("GooglePlayServices not Available")
        }
        loadLocalization()
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = (activity as MainActivity).viewModel
        binding.viewModel = viewModel

        binding.noteTitle.requestFocus()
        binding.cancel.setOnClickListener {
            viewModel.cancel()
            it.findNavController().popBackStack()
        }

        binding.addNote.setOnClickListener {
            viewModel.insert()
            it.findNavController().popBackStack()
        }
    }

    private fun loadLocalization() {
        val access = PackageManager.PERMISSION_GRANTED
        val hasFineLocationPermission = ActivityCompat.checkSelfPermission(requireContext(), FINE_LOCATION) == access
        val hasCourseLocationPermission =  ActivityCompat.checkSelfPermission(requireContext(), COARSE_LOCATION) == access
        val hasPermission = hasFineLocationPermission || hasCourseLocationPermission

        if (!hasPermission) {
            locationPermissionRequest.launch(arrayOf(FINE_LOCATION, COARSE_LOCATION))
            return
        }

        locationProvider.lastLocation.addOnSuccessListener { location ->
            if (location != null) {
                viewModel.latitude.value = location.latitude
                viewModel.longitude.value = location.longitude
            }
        }
    }

    private fun isGooglePlayServicesAvailable(): Int {
        return GooglePlayServicesUtil.isGooglePlayServicesAvailable(requireContext())
    }
    private fun requestPermission(): ActivityResultLauncher<Array<String>> {
        return registerForActivityResult(
            ActivityResultContracts.RequestMultiplePermissions()
        ) { permissions ->
            when {
                permissions.getOrDefault(FINE_LOCATION, false) -> {
                    loadLocalization()
                }
                permissions.getOrDefault(COARSE_LOCATION, false) -> {
                    loadLocalization()
                } else -> {
                show("Default Localization")
            }
            }
        }
    }

    private fun show(message: String) {
        Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT).show()
    }
}
