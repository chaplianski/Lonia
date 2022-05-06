package com.example.lonia.domain.repository

import com.example.lonia.domain.model.Questionnaires

interface QuestionnairesRepository {

 //   suspend fun getQuestionnaires(): List<Questionnaires>

    suspend fun getQuestionnaires(): List<Questionnaires>
}
