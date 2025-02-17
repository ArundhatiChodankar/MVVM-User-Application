package com.example.mvvmuserapplication.view.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mvvmuserapplication.databinding.FragmentUserListBinding
import com.example.mvvmuserapplication.model.data.model.User
import com.example.mvvmuserapplication.model.data.retrofit.APIResponse
import com.example.mvvmuserapplication.view.activity.MainActivity
import com.example.mvvmuserapplication.view.adapter.UserAdapter
import com.example.mvvmuserapplication.view.interfaces.OnItemClickListener
import com.example.mvvmuserapplication.viewmodel.viewmodel.UserViewModel

class UserListFragment : Fragment() , OnItemClickListener {

    private var binding: FragmentUserListBinding? = null
    private lateinit var userViewModel: UserViewModel
    private lateinit var userAdapter : UserAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = FragmentUserListBinding.inflate(layoutInflater)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        // Inflate the layout for this fragment
        return binding?.root
    }

    private fun setUpRecyclerView() {
        binding?.allUsersRV?.apply {
            adapter = UserAdapter(requireActivity(),this@UserListFragment)
            layoutManager = LinearLayoutManager(context)
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        userViewModel = (activity as MainActivity).userViewModel

        userViewModel.getUsersList()

        setUpRecyclerView()


        userViewModel.observeGetAllUsersResponse().observe(
            viewLifecycleOwner, {
                when (it) {
                    is APIResponse.Success -> {
                        userAdapter.differ.submitList(it.data?.data)
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


    override fun onDeleteClick(id: String) {
        TODO("Not yet implemented")
    }

    override fun onItemClick(user: User) {
        TODO("Not yet implemented")
    }


}