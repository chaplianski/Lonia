package com.example.testtask.data.repository

import android.util.Log
import com.example.testtask.data.storage.model.QuestionnairesDataResponse
import com.example.testtask.data.storage.network.retrofit.QuestionnairesApiHelper
import com.example.testtask.domain.model.Questionnaires
import com.example.testtask.domain.model.QuestionnariesResponse
import com.example.testtask.domain.model.QuestionsResponse
import com.example.testtask.domain.repository.QuestionnairesRepository
import javax.inject.Inject

class QuestionnairesRepositoryImpl @Inject constructor(
    private val questionnairesApiHelper: QuestionnairesApiHelper
) : QuestionnairesRepository {

 //   override suspend fun getQuestionnaires(): List<Questionnaires> {
 //      return questionnairesStorage.getQuestionnaires().map { it.questionnairesMapDataToDomain() }
 //   }

    override suspend fun getQuestionnaires(): QuestionnariesResponse {
        val questionnariesResponse = questionnairesApiHelper.getQuestionnaires()
        return QuestionnariesResponse(
            questionnariesResponse.response?.map {it.questionnairesMapDataToDomain()},
            questionnariesResponse.status
        )

    }

}