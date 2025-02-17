package com.example.mvvmuserapplication.model.data.retrofit

import android.content.Context
import com.example.mvvmuserapplication.model.data.SessionManager
import com.example.mvvmuserapplication.model.data.retrofit.APIConstants.Companion.BASE_URL
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Response
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitInstance {
    companion object {
        private val retrofit by lazy {
            val logging = HttpLoggingInterceptor()
            logging.setLevel(HttpLoggingInterceptor.Level.BODY)

            val okHttpClient = OkHttpClient.Builder()
                .addInterceptor(logging)  // Logs the HTTP requests and responses
              //  .addInterceptor(AuthInterceptor())  // Adds authentication headers
                .build()

            Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        }

        val userAPI: UserAPI by lazy {
            retrofit.create(UserAPI::class.java)
        }

        // AuthInterceptor added to handle token authorization
       /* class AuthInterceptor : Interceptor {
            private val sessionManager = SessionManager  // Assuming your SessionManager is already implemented

            override fun intercept(chain: Interceptor.Chain): Response {
                val requestBuilder = chain.request().newBuilder()

                // Fetch token from session manager and add to request if available
                sessionManager.fetchAuthToken()?.let {
                    requestBuilder.addHeader("Authorization", "Bearer $it")
                }

                return chain.proceed(requestBuilder.build())  // Proceed with the modified request
            }
        }*/
    }
}
