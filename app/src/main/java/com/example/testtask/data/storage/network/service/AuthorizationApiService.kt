package com.example.testtask.data.storage.network.service

import com.example.testtask.data.storage.model.LoginResponseData
import retrofit2.Response
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface AuthorizationApiService {

    @FormUrlEncoded
    @POST("login")
    suspend fun fetchToken (@Field("email") email: String, @Field("password") password: String) : Response<LoginResponseData>
}