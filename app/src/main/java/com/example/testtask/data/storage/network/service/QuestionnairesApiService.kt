package com.example.testtask.data.storage.network.service

import com.example.testtask.data.storage.model.QuestionnairesData
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Call
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Path
import retrofit2.http.Query

interface QuestionnairesApiService {

    @GET("question")
    suspend fun fetchQuestionnaires(@Header("Authorization") token: String) : Response<List<QuestionnairesData>>
}