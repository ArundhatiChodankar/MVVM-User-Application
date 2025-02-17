package com.example.mvvmuserapplication.view.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.mvvmuserapplication.databinding.FragmentRegisterBinding
import com.example.mvvmuserapplication.model.data.model.RegisterRequest
import com.example.mvvmuserapplication.model.data.retrofit.APIResponse
import com.example.mvvmuserapplication.view.activity.MainActivity
import com.example.mvvmuserapplication.viewmodel.viewmodel.UserViewModel


class RegisterFragment : Fragment() {

    private var binding: FragmentRegisterBinding? = null
    private lateinit var userViewModel: UserViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = FragmentRegisterBinding.inflate(layoutInflater)
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
            registerBTN.setOnClickListener {

                val registerRequest =
                    RegisterRequest(emailET.text.toString(), passwordET.text.toString())
                userViewModel.registerUser(registerRequest)
            }
            signUpTV.setOnClickListener {
                findNavController().navigate(RegisterFragmentDirections.actionRegisterFragmentToLoginFragment())
            }
        }



        userViewModel.observeRegisterResponse().observe(
            viewLifecycleOwner, {
                when (it) {
                    is APIResponse.Success -> {
                        Toast.makeText(context, "Registration Successful", Toast.LENGTH_SHORT)
                            .show()
                        findNavController().navigateUp()
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