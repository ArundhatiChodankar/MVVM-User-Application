package com.example.mvvmuserapplication.model.repository

import com.example.mvvmuserapplication.model.data.model.LoginRequest
import com.example.mvvmuserapplication.model.data.model.RegisterRequest
import com.example.mvvmuserapplication.model.data.model.User
import com.example.mvvmuserapplication.model.data.retrofit.RetrofitInstance
import com.example.mvvmuserapplication.model.data.room.AppDatabase

class UserRepository(private val db: AppDatabase) {

    suspend fun getUsersList() = RetrofitInstance.userAPI.getUsersList()

    suspend fun getUserDetailsById(id: String) = RetrofitInstance.userAPI.getUserDetailsById(id)

    suspend fun loginUser(loginRequest: LoginRequest) =
        RetrofitInstance.userAPI.loginUser(loginRequest)

    suspend fun registerUser(registerRequest: RegisterRequest) =
        RetrofitInstance.userAPI.registerUser(registerRequest)


    suspend fun insertFavouriteUser(user: User) = db.getUserDao().insertFavouriteUser(user)

    suspend fun deleteFromFavourite(id: String) = db.getUserDao().deleteFromFavourite(id)

    suspend fun getAllFavouriteUsers() = db.getUserDao().getAllFavouriteUsers()


}