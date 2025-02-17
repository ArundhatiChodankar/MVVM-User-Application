package com.example.mvvmuserapplication.view.fragment

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.example.mvvmuserapplication.databinding.FragmentLoginBinding
import com.example.mvvmuserapplication.model.data.model.LoginRequest
import com.example.mvvmuserapplication.model.data.retrofit.APIResponse
import com.example.mvvmuserapplication.view.activity.HomeActivity
import com.example.mvvmuserapplication.view.activity.MainActivity
import com.example.mvvmuserapplication.viewmodel.viewmodel.UserViewModel


class LoginFragment : Fragment() {

    private var binding: FragmentLoginBinding? = null
    private lateinit var userViewModel: UserViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = FragmentLoginBinding.inflate(layoutInflater)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        // Inflate the layout for this fragment
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        userViewModel = (activity as MainActivity).userViewModel

        binding?.apply {
            loginBTN.setOnClickListener {

                val loginRequest = LoginRequest(emailET.text.toString(), passwordET.text.toString())
                userViewModel.loginUser(loginRequest)
            }
            signUpTV.setOnClickListener{
                findNavController().navigate(LoginFragmentDirections.actionLoginFragmentToRegisterFragment())
            }
        }

        userViewModel.observeLoginResponse().observe(
            viewLifecycleOwner, {
                when (it) {
                    is APIResponse.Success -> {
                        Toast.makeText(context, "Login Successful", Toast.LENGTH_SHORT).show()
                        startActivity(Intent(context, HomeActivity::class.java))
                    }

                    is APIResponse.Error ->
                        Toast.makeText(context, it.message, Toast.LENGTH_SHORT).show()

                    is APIResponse.Loading -> {}
                }
            }
        )

    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }

}