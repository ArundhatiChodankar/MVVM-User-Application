package com.example.mvvmuserapplication.model.repository

import com.example.mvvmuserapplication.model.data.model.LoginRequest
import com.example.mvvmuserapplication.model.data.model.RegisterRequest
import com.example.mvvmuserapplication.model.data.model.User
import com.example.mvvmuserapplication.model.data.retrofit.RetrofitInstance
import com.example.mvvmuserapplication.model.data.room.AppDatabase

class UserRepository(private val db: AppDatabase) : BaseRepo() {

    suspend fun getUsersList() = safeApiCall { RetrofitInstance.userAPI.getUsersList() }

    suspend fun getUserDetailsById(id: String) =
        safeApiCall { RetrofitInstance.userAPI.getUserDetailsById(id) }

    suspend fun loginUser(loginRequest: LoginRequest) =
        safeApiCall { RetrofitInstance.userAPI.loginUser(loginRequest) }


    suspend fun registerUser(registerRequest: RegisterRequest) = safeApiCall {
        RetrofitInstance.userAPI.registerUser(registerRequest)
    }


    suspend fun insertFavouriteUser(user: User) = db.getUserDao().insertFavouriteUser(user)

    suspend fun deleteFromFavourite(id: String) = db.getUserDao().deleteFromFavourite(id)

    suspend fun getAllFavouriteUsers() = db.getUserDao().getAllFavouriteUsers()


}