package com.example.mvvmuserapplication.view.fragment

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mvvmuserapplication.R
import com.example.mvvmuserapplication.databinding.FragmentFavouriteBinding
import com.example.mvvmuserapplication.model.data.model.User
import com.example.mvvmuserapplication.model.data.retrofit.APIResponse
import com.example.mvvmuserapplication.view.activity.MainActivity
import com.example.mvvmuserapplication.view.activity.UserDetailsActivity
import com.example.mvvmuserapplication.view.adapter.FavouriteAdapter
import com.example.mvvmuserapplication.view.interfaces.OnItemClickListener
import com.example.mvvmuserapplication.viewmodel.viewmodel.UserViewModel


class FavouriteFragment : Fragment() , OnItemClickListener {

    private var binding: FragmentFavouriteBinding? = null
    private lateinit var userViewModel: UserViewModel
    private lateinit var favUserAdapter : FavouriteAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = FragmentFavouriteBinding.inflate(layoutInflater)
        favUserAdapter = FavouriteAdapter(requireContext(),this)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        // Inflate the layout for this fragment
        return binding?.root
    }

    private fun setUpRecyclerView() {
        binding?.favUsersRV?.apply {
            layoutManager = LinearLayoutManager(context)
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        userViewModel = (activity as MainActivity).userViewModel

        userViewModel.getAllFavouriteUsers()

        setUpRecyclerView()


        userViewModel.getAllFavouriteUsers().observe(
            viewLifecycleOwner, {
                   favUserAdapter.differ.submitList(it)
            }
        )

    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }


    override fun onDeleteClick(id: String) {
        userViewModel.deleteFromFavourite(id)
    }

    override fun onItemClick(user: User) {
        startActivity(Intent(requireContext(),UserDetailsActivity::class.java))
    }


}