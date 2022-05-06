package com.example.lonia.data.repository

import com.example.lonia.data.storage.network.retrofit.QuestionnairesApiHelper
import com.example.lonia.domain.model.Questionnaires
import com.example.lonia.domain.repository.QuestionnairesRepository
import javax.inject.Inject

class QuestionnairesRepositoryImpl @Inject constructor(
    private val questionnairesApiHelper: QuestionnairesApiHelper
) : QuestionnairesRepository {

 //   override suspend fun getQuestionnaires(): List<Questionnaires> {
 //      return questionnairesStorage.getQuestionnaires().map { it.questionnairesMapDataToDomain() }
 //   }

    override suspend fun getQuestionnaires(): List<Questionnaires> {
        return questionnairesApiHelper.getQuestionnaires().map {it.questionnairesMapDataToDomain()}
    }

}