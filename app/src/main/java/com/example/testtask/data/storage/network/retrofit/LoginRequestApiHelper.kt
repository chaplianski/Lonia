package com.example.testtask.data.storage.network.retrofit

import com.example.testtask.data.storage.model.LoginRequestData
import com.example.testtask.data.storage.model.LoginResponseData
import com.example.testtask.data.storage.network.service.AuthorizationApiService
import retrofit2.Retrofit
import javax.inject.Inject

class LoginRequestApiHelper @Inject constructor() {

    @Inject
    lateinit var loginRequestretrofit: Retrofit

    suspend fun fetchToken(loginRequestData: LoginRequestData): LoginResponseData{

        val retrofit = loginRequestretrofit.create(AuthorizationApiService::class.java)
        val responseToken = retrofit.fetchToken(loginRequestData.email, loginRequestData.password)
        return responseToken.body()!!
    }
}