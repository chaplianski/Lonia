package com.example.lonia.data.storage.network.retrofit

import android.content.Context
import android.util.Log
import com.example.lonia.R
import com.example.lonia.data.storage.model.InfoBriefcaseData
import com.example.lonia.data.storage.model.QuestionnairesData
import com.example.lonia.data.storage.network.service.InfoBriefcaseApiService
import com.example.lonia.domain.exceptions.NetworkException
import retrofit2.Retrofit
import javax.inject.Inject

class InfoBriefcaseApiHelper @Inject constructor() {

    @Inject
    lateinit var infoBriefcaseRetrofit: Retrofit
    @Inject
    lateinit var context: Context

    suspend fun getInfoBriefcase(): InfoBriefcaseData {

        val retrofit = infoBriefcaseRetrofit.create(InfoBriefcaseApiService::class.java)
        var listInfoBriefcaseData = InfoBriefcaseData(emptyList(), emptyList(), emptyList(),
            emptyList())

        val sharedPref = context.getSharedPreferences("Net pref", Context.MODE_PRIVATE)
        val token = sharedPref?.getString(NetParameters.TOKEN, "")

        val responseInfoBriefcase = retrofit.fetchInfoBriefcase("Bearer $token")

        when (responseInfoBriefcase.code()) {
            in 200..299 -> {
                listInfoBriefcaseData = responseInfoBriefcase.body() ?: InfoBriefcaseData(emptyList(), emptyList(), emptyList(),
                    emptyList())
            }
            in 300..399 -> {
                throw NetworkException(R.string.internet_error)
            }
            in 400..499 -> {
                throw NetworkException(R.string.client_error)
            }
            in 500..599 -> {
                throw NetworkException(R.string.server_error)
            }
        }
        return listInfoBriefcaseData
    }
}