package com.example.mvvmuserapplication.view.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation.setViewNavController
import androidx.navigation.fragment.NavHostFragment
import com.example.mvvmuserapplication.R
import com.example.mvvmuserapplication.databinding.ActivityMainBinding
import com.example.mvvmuserapplication.model.data.room.AppDatabase
import com.example.mvvmuserapplication.model.repository.UserRepository
import com.example.mvvmuserapplication.viewmodel.viewmodel.UserViewModel
import com.example.mvvmuserapplication.viewmodel.viewmodelfactory.UserViewModelProviderFactory

class MainActivity : AppCompatActivity() {
    private var binding: ActivityMainBinding? = null
    lateinit var userViewModel: UserViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding!!.root)

        setUpViewModel()

        setUpNavigationComponent()

    }

    private fun setUpNavigationComponent() {
        val navigationHost =
            supportFragmentManager.findFragmentById(R.id.fragmentContainerView) as NavHostFragment
        val navController = navigationHost.navController

        setViewNavController(binding?.fragmentContainerView!!, navController)
    }

    private fun setUpViewModel() {
        val userRepository = UserRepository(AppDatabase(this))
        val viewModelFactory = UserViewModelProviderFactory(userRepository)
        userViewModel = ViewModelProvider(this, viewModelFactory)[UserViewModel::class]

    }
}