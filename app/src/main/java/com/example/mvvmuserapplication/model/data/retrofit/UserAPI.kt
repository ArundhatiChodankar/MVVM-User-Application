package com.example.mvvmuserapplication.model.data.retrofit

import com.example.mvvmuserapplication.model.data.model.GetAllUsersResponse
import com.example.mvvmuserapplication.model.data.model.LoginRequest
import com.example.mvvmuserapplication.model.data.model.LoginResponse
import com.example.mvvmuserapplication.model.data.model.RegisterRequest
import com.example.mvvmuserapplication.model.data.model.RegisterResponse
import com.example.mvvmuserapplication.model.data.model.UserDetailsResponse
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface UserAPI {

    @GET("/api/users?page=2")
    fun getUsersList(): APIResponse<GetAllUsersResponse>

    @GET("/api/users/{id}")
    fun getUserDetailsById(@Path("id") id: String): APIResponse<UserDetailsResponse>

    @POST("/api/login")
    fun loginUser(@Body loginRequest: LoginRequest): APIResponse<LoginResponse>

    @POST("/api/register")
    fun registerUser(@Body registerRequest: RegisterRequest): APIResponse<RegisterResponse>

}