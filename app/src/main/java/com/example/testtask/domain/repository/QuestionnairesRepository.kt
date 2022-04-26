package com.example.testtask.domain.repository

import com.example.testtask.domain.model.Questionnaires

interface QuestionnairesRepository {

 //   suspend fun getQuestionnaires(): List<Questionnaires>

    suspend fun getQuestionnaires(): List<Questionnaires>
}
