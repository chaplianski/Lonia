package com.example.testtask.data.storage.network.retrofit

import android.util.Log
import com.example.testtask.data.repository.questionnairesMapDataToDomain
import com.example.testtask.data.storage.model.QuestionnairesDataResponse
import com.example.testtask.data.storage.network.service.QuestionnairesApiService
import retrofit2.Retrofit
import java.io.IOException
import java.net.ConnectException
import java.net.UnknownHostException
import javax.inject.Inject

class QuestionnairesApiHelper @Inject constructor() {

    @Inject
    lateinit var questionnairesRetrofit: Retrofit

    //   suspend fun getQuestionnaires(): List<QuestionnairesData>{

    suspend fun getQuestionnaires(): QuestionnairesDataResponse {

        val retrofit = questionnairesRetrofit.create(QuestionnairesApiService::class.java)
        var questionnairesDataResponse = QuestionnairesDataResponse()

        try {
            val responseQuestionnaires = retrofit.fetchQuestionnaires("Bearer ${NetParameters.TOKEN}")
            when (responseQuestionnaires.code()) {
                in 200..299 -> {
                    val questionnairesList = responseQuestionnaires.body()
                    questionnairesDataResponse =
                        questionnairesList?.let { QuestionnairesDataResponse(response = it, status = "Success") }!!
                }
                in 300..399 -> {
                    questionnairesDataResponse = QuestionnairesDataResponse(status = "Redirects Error. Code: ${responseQuestionnaires.code()}")
                }
                in 400..499 -> {
                    questionnairesDataResponse = QuestionnairesDataResponse(status = "Client Error. Code: ${responseQuestionnaires.code()}")
                }
                in 500..599 -> {
                    questionnairesDataResponse = QuestionnairesDataResponse(status = "Server Error. Code: ${responseQuestionnaires.code()}")
                }
            }
        }catch (e: IOException){
            questionnairesDataResponse = QuestionnairesDataResponse(status = "Check your Internet connection")
        }catch (e: UnknownHostException){
            questionnairesDataResponse = QuestionnairesDataResponse(status = "Check your Internet connection")
        }catch (e: ConnectException){
            questionnairesDataResponse = QuestionnairesDataResponse(status = "Check your Internet connection")
        }catch (e: Exception) {
            questionnairesDataResponse = QuestionnairesDataResponse(status = "Unknown Error. Restart the app.")
        }

        return questionnairesDataResponse
    }

}
