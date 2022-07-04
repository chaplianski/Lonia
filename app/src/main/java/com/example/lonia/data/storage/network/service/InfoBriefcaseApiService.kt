package com.example.lonia.data.storage.network.service

import com.example.lonia.data.storage.model.InfoBriefcaseData
import com.example.lonia.data.storage.model.QuestionnairesData
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Header

interface InfoBriefcaseApiService {
    @GET("api/infobiefcase")
    suspend fun fetchInfoBriefcase(@Header("Authorization") token: String): Response<InfoBriefcaseData>
}