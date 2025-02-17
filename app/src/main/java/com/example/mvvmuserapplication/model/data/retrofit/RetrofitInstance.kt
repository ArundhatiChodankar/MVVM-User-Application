package com.example.mvvmuserapplication.model.data.retrofit

import android.content.Context
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
                .addInterceptor(logging)
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

       /* class AuthInterceptor(context: Context) : Interceptor {
            private val sessionManager = SessionManager(context)

            override fun intercept(chain: Interceptor.Chain): Response {
                val requestBuilder = chain.request().newBuilder()

                // If token has been saved, add it to the request
                sessionManager.fetchAuthToken()?.let {
                    requestBuilder.addHeader("Authorization", "Bearer $it")
                }

                return chain.proceed(requestBuilder.build())
            }
        }*/

    }

}