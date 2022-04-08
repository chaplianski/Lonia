package com.example.testtask.data.storage.network.retrofit

import android.util.Log
import com.example.testtask.data.storage.model.QuestionnairesData
import com.example.testtask.data.storage.model.QuestionsData
import com.example.testtask.data.storage.network.service.QuestionnairesApiService
import com.example.testtask.data.storage.network.service.QuestionsApiService
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.RequestBody.Companion.toRequestBody
import org.json.JSONObject
import retrofit2.Retrofit
import javax.inject.Inject

class NetQuestionsRepository @Inject constructor() {

    @Inject
    lateinit var questionnairesRetrofit: Retrofit

    suspend fun getQuestions(qidcode: Int): List<QuestionsData> {
        val retrofit = questionnairesRetrofit.create(QuestionsApiService::class.java)

        val json = JSONObject()
        json.put("qid", qidcode)
        val sendData = json.toString().toRequestBody("application/json".toMediaTypeOrNull())

        val response = retrofit.fetchQuestions("Bearer ${NetParameters.TOKEN}", sendData)

        return response.body()!!
    }
}