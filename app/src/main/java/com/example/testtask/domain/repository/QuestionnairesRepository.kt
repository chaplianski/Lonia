package com.example.testtask.domain.repository

import com.example.testtask.data.storage.model.QuestionnairesDataResponse
import com.example.testtask.domain.model.BriefCase
import com.example.testtask.domain.model.Questionnaires
import com.example.testtask.domain.model.QuestionnariesResponse

interface QuestionnairesRepository {

 //   suspend fun getQuestionnaires(): List<Questionnaires>

    suspend fun getQuestionnaires(): QuestionnariesResponse
}
