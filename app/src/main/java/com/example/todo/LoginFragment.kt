package com.example.todo

import androidx.fragment.app.Fragment
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import com.example.todo.databinding.FragmentLoginBinding

import com.example.todo.data.LoginResult
import com.example.todo.view.LoginViewModel

class LoginFragment : Fragment() {

    private lateinit var loginViewModel: LoginViewModel
    private lateinit var binding: FragmentLoginBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_login ,container, false)
        loginViewModel = (activity as MainActivity).viewModelLogin
        isLogged()

        return binding.root
    }

    private fun isLogged () {
        if(loginViewModel.isLogged()) {
            binding.root.findNavController().navigate(R.id.action_loginFragment_to_landingFragment)
        }
        binding.login.setOnClickListener {
            val username = binding.username.text.toString()
            val password = binding.password.text.toString()
            updateUiWithUser(loginViewModel.login(username, password).value)
        }
    }

    private fun updateUiWithUser(model: LoginResult?) {
        if (model?.error != null) {
            binding.errorMsg.text = getString(model.error)
        } else {
            showLoginMessage(model?.success?.displayName ?: "")
            binding.root.findNavController().navigate(R.id.action_loginFragment_to_landingFragment)
        }
    }

    private fun showLoginMessage(string: String) {
        val appContext = context?.applicationContext ?: return
        Toast.makeText(appContext, string, Toast.LENGTH_LONG).show()
    }

}