package com.example.testtask.data.storage.network.retrofit

import android.util.Log
import com.example.testtask.data.storage.model.QuestionnairesData
import com.example.testtask.data.storage.network.service.QuestionnairesApiService
import retrofit2.Retrofit
import javax.inject.Inject

class QuestionnairesStorageImpl @Inject constructor() {

    @Inject
    lateinit var questionnairesRetrofit: Retrofit

    suspend fun getQuestionnaires(): List<QuestionnairesData>{
        val retrofit = questionnairesRetrofit.create(QuestionnairesApiService::class.java)
        val response = retrofit.fetchQuestionnaires("Bearer ${NetParameters.TOKEN}")

        if (response.code() == 200) Log.d("My Log", "Code response: 200")
        else {
            //   val error = ErrorUtils.(response)
            Log.d("My Log", "Internet connection problem. Responce code: ${response.code()} Error body: ${response.errorBody()}")
        }


        return response.body()!!
    }
}