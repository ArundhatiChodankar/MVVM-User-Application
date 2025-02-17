package com.example.mvvmuserapplication.view.activity

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation.setViewNavController
import androidx.navigation.fragment.NavHostFragment
import com.example.mvvmuserapplication.R
import com.example.mvvmuserapplication.databinding.ActivityHomeBinding
import com.example.mvvmuserapplication.model.data.room.AppDatabase
import com.example.mvvmuserapplication.model.repository.UserRepository
import com.example.mvvmuserapplication.viewmodel.viewmodel.UserViewModel
import com.example.mvvmuserapplication.viewmodel.viewmodelfactory.UserViewModelProviderFactory

class HomeActivity : AppCompatActivity() {

    private var binding: ActivityHomeBinding? = null
    lateinit var userViewModel: UserViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding!!.root)

        setUpViewModel()

        setUpNavigationComponent()

    }

    private fun setUpNavigationComponent() {
        val navigationHost =
            supportFragmentManager.findFragmentById(R.id.homeFragmentContainerView) as NavHostFragment
        val navController = navigationHost.navController

        setViewNavController(binding?.homeFragmentContainerView!!, navController)
    }

    private fun setUpViewModel() {
        val userRepository = UserRepository(AppDatabase(this))
        val viewModelFactory = UserViewModelProviderFactory(userRepository)
        userViewModel = ViewModelProvider(this, viewModelFactory)[UserViewModel::class]

    }
}