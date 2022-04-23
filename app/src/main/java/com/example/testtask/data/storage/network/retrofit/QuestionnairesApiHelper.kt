package com.example.testtask.data.storage.network.retrofit

import android.util.Log
import com.example.testtask.R
import com.example.testtask.data.repository.questionnairesMapDataToDomain
import com.example.testtask.data.storage.model.QuestionnairesData
import com.example.testtask.data.storage.model.QuestionnairesDataResponse
import com.example.testtask.data.storage.network.service.QuestionnairesApiService
import com.example.testtask.domain.exceptions.NetworkException
import retrofit2.Retrofit
import java.io.IOException
import java.net.ConnectException
import java.net.UnknownHostException
import javax.inject.Inject

class QuestionnairesApiHelper @Inject constructor() {

    @Inject
    lateinit var questionnairesRetrofit: Retrofit

    //   suspend fun getQuestionnaires(): List<QuestionnairesData>{

    suspend fun getQuestionnaires(): List<QuestionnairesData> {

        val retrofit = questionnairesRetrofit.create(QuestionnairesApiService::class.java)
        var listQuestionnairesData: List<QuestionnairesData> = emptyList()

        val responseQuestionnaires = retrofit.fetchQuestionnaires("Bearer ${NetParameters.TOKEN}")
        when (responseQuestionnaires.code()) {
            in 200..299 -> {
                listQuestionnairesData = responseQuestionnaires.body() ?: emptyList()
            }
            in 300..399 -> {
                throw NetworkException(R.string.redirect_error)
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
