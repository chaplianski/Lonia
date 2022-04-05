package com.example.testtask.data.storage.network.retrofit

import com.example.testtask.data.storage.model.QuestionnairesData
import com.example.testtask.data.storage.network.service.QuestionnairesApiService
import com.example.testtask.data.storage.storagies.QuestionnairesStorage
import retrofit2.Retrofit
import javax.inject.Inject

class QuestionnairesStorageImpl @Inject constructor() : QuestionnairesStorage {

    @Inject
    lateinit var questionnairesRetrofit: Retrofit

    suspend fun getQuestionnaires(): List<QuestionnairesData>{
        val retrofit = questionnairesRetrofit.create(QuestionnairesApiService::class.java)
        val response = retrofit.fetchQuestionnaires("Bearer ${NetParameters.TOKEN}")
        return response.body()!!

    }


}