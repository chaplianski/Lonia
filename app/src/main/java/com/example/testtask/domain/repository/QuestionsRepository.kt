package com.example.testtask.domain.repository

import com.example.testtask.data.storage.model.QuestionsDataResponse
import com.example.testtask.domain.model.Answers
import com.example.testtask.domain.model.Questions
import com.example.testtask.domain.model.QuestionsResponse

interface QuestionsRepository {

    fun getQuestions (briefcaseId: Long): List<Questions>

    suspend fun fetchQuestions (qid: Int): QuestionsResponse

    fun updateQuestions(questions: Questions, answers: Answers)
}