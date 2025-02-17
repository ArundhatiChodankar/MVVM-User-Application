package com.example.mvvmuserapplication.viewmodel.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mvvmuserapplication.model.data.model.GetAllUsersResponse
import com.example.mvvmuserapplication.model.data.model.LoginRequest
import com.example.mvvmuserapplication.model.data.model.LoginResponse
import com.example.mvvmuserapplication.model.data.model.RegisterRequest
import com.example.mvvmuserapplication.model.data.model.RegisterResponse
import com.example.mvvmuserapplication.model.data.model.User
import com.example.mvvmuserapplication.model.data.model.UserDetailsResponse
import com.example.mvvmuserapplication.model.data.retrofit.APIResponse
import com.example.mvvmuserapplication.model.repository.UserRepository
import kotlinx.coroutines.launch

class UserViewModel(private val userRepository: UserRepository) : ViewModel() {

    private lateinit var getAllUsersResponseMD: MutableLiveData<APIResponse<GetAllUsersResponse>>
    private lateinit var userDetailsResponseMD: MutableLiveData<APIResponse<UserDetailsResponse>>
    private lateinit var loginResponseMD: MutableLiveData<APIResponse<LoginResponse>>
    private lateinit var registerResponseMD: MutableLiveData<APIResponse<RegisterResponse>>

    suspend fun getUsersList() {
        viewModelScope.launch {
            getAllUsersResponseMD.postValue(APIResponse.Loading())
            val response = userRepository.getUsersList()
            getAllUsersResponseMD.postValue(response)
        }
    }

    suspend fun getUserDetailsById(id: String) {
        viewModelScope.launch {
            userDetailsResponseMD.postValue(APIResponse.Loading())
            val response = userRepository.getUserDetailsById(id)
            userDetailsResponseMD.postValue(response)
        }
    }

    suspend fun loginUser(loginRequest: LoginRequest) {
        viewModelScope.launch {
            loginResponseMD.postValue(APIResponse.Loading())
            val response = userRepository.loginUser(loginRequest)
            loginResponseMD.postValue(response)
        }
    }

    suspend fun registerUser(registerRequest: RegisterRequest) {
        viewModelScope.launch {
            registerResponseMD.postValue(APIResponse.Loading())
            val response = userRepository.registerUser(registerRequest)
            registerResponseMD.postValue(response)
        }
    }

    fun observeGetAllUsersResponse(): LiveData<APIResponse<GetAllUsersResponse>> {
        return getAllUsersResponseMD
    }

    fun observeUserDetailsResponse(): LiveData<APIResponse<UserDetailsResponse>> {
        return userDetailsResponseMD
    }

    fun observeLoginResponse(): LiveData<APIResponse<LoginResponse>> {
        return loginResponseMD
    }

    fun observeRegisterResponse(): LiveData<APIResponse<RegisterResponse>> {
        return registerResponseMD
    }


    suspend fun insertFavouriteUser(user: User){
        viewModelScope.launch {
            userRepository.insertFavouriteUser(user)
        }
    }

    suspend fun deleteFromFavourite(id: String){
        viewModelScope.launch {
            userRepository.deleteFromFavourite(id)
        }
    }
    suspend fun getAllFavouriteUsers(){
        viewModelScope.launch {
            userRepository.getAllFavouriteUsers()
        }
    }

}