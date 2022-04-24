package com.example.testtask.data.storage.network.service

import com.example.testtask.data.storage.model.LoginResponseData
import com.example.testtask.data.storage.model.QuestionsData
import okhttp3.RequestBody
import retrofit2.Response
import retrofit2.http.*

interface AuthorizationApiService {

    @Headers("Content-Type: application/json")
    @POST("/login")
    suspend fun fetchToken(
        @Body login: RequestBody
    ): Response<LoginResponseData>

}