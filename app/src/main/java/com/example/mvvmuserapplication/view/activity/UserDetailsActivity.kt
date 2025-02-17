package com.example.mvvmuserapplication.view.activity

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.ViewModelProvider
import com.example.mvvmuserapplication.R
import com.example.mvvmuserapplication.databinding.ActivityUserDetailsBinding
import com.example.mvvmuserapplication.model.data.room.AppDatabase
import com.example.mvvmuserapplication.model.repository.UserRepository
import com.example.mvvmuserapplication.viewmodel.viewmodel.UserViewModel
import com.example.mvvmuserapplication.viewmodel.viewmodelfactory.UserViewModelProviderFactory

class UserDetailsActivity : AppCompatActivity() {

    private var binding: ActivityUserDetailsBinding? = null
    lateinit var userViewModel: UserViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityUserDetailsBinding.inflate(layoutInflater)
        setContentView(binding!!.root)

        setUpViewModel()



    }
    private fun setUpViewModel() {
        val userRepository = UserRepository(AppDatabase(this))
        val viewModelFactory = UserViewModelProviderFactory(userRepository)
        userViewModel = ViewModelProvider(this, viewModelFactory)[UserViewModel::class]

    }
}