package com.example.testtask.domain.repository

import com.example.testtask.domain.model.Questions

interface QuestionsRepository {

    fun getQuestions (): List<Questions>

    fun addQuestions (question: Questions)

    suspend fun fetchQuestions (qid: Int): List<Questions>
}