package com.example.lonia.data.storage.network.service

import com.example.lonia.data.storage.model.LoginResponseData
import okhttp3.RequestBody
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.Headers
import retrofit2.http.POST

interface AuthorizationApiService {

    @Headers("Content-Type: application/json")
    @POST("/login")
    suspend fun fetchToken(
        @Body login: RequestBody
    ): Response<LoginResponseData>

}