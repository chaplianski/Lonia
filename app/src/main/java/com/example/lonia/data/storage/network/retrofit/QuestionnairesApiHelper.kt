package com.example.lonia.data.storage.network.retrofit

import android.content.Context
import com.example.lonia.R
import com.example.lonia.data.storage.model.QuestionnairesData
import com.example.lonia.data.storage.network.service.QuestionnairesApiService
import com.example.lonia.domain.exceptions.NetworkException
import retrofit2.Retrofit
import javax.inject.Inject

class QuestionnairesApiHelper @Inject constructor() {

    @Inject
    lateinit var questionnairesRetrofit: Retrofit
    @Inject
    lateinit var context: Context

    suspend fun getQuestionnaires(): List<QuestionnairesData> {

        val retrofit = questionnairesRetrofit.create(QuestionnairesApiService::class.java)
        var listQuestionnairesData: List<QuestionnairesData> = emptyList()

        val sharedPref = context.getSharedPreferences("Net pref", Context.MODE_PRIVATE)
        val token = sharedPref?.getString(NetParameters.TOKEN, "")

        val responseQuestionnaires = retrofit.fetchQuestionnaires("Bearer $token")

        when (responseQuestionnaires.code()) {
            in 200..299 -> {
                listQuestionnairesData = responseQuestionnaires.body() ?: emptyList()
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
        return listQuestionnairesData
    }
}
