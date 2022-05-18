package com.example.lonia.data.storage.network.retrofit

import android.content.Context
import android.util.Log
import com.example.lonia.R
import com.example.lonia.data.storage.model.QuestionsData
import com.example.lonia.data.storage.network.service.QuestionsApiService
import com.example.lonia.domain.exceptions.NetworkException
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.RequestBody.Companion.toRequestBody
import org.json.JSONObject
import retrofit2.Retrofit
import javax.inject.Inject

class QuestionsApiHelper @Inject constructor() {

    @Inject
    lateinit var questionnairesRetrofit: Retrofit

    @Inject
    lateinit var context: Context

    suspend fun getQuestions(qidcode: Int): List<QuestionsData> {
        val retrofit = questionnairesRetrofit.create(QuestionsApiService::class.java)
        var questionsDataResponse: List<QuestionsData> = emptyList()

        val json = JSONObject()
        json.put("qid", qidcode)
        val sendData = json.toString().toRequestBody("application/json".toMediaTypeOrNull())

        val sharedPref = context.getSharedPreferences("Net pref", Context.MODE_PRIVATE)
        val token = sharedPref?.getString(NetParameters.TOKEN, "")

        val responseQuestions = retrofit.fetchQuestions("Bearer $token", sendData)

        when (responseQuestions.code()) {
            in 200..299 -> {
                questionsDataResponse = responseQuestions.body() ?: emptyList()
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
        return questionsDataResponse
    }
}

