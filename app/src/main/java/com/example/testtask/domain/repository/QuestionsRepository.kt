package com.example.testtask.domain.repository

import com.example.testtask.domain.model.Answers
import com.example.testtask.domain.model.Questions

interface QuestionsRepository {

    fun getQuestions (briefcaseId: Long): List<Questions>

    suspend fun fetchQuestions (qid: Int): List<Questions>

    fun updateQuestions(questions: Questions, answers: Answers)

    fun getNotAnsweredQuestions(briefcaseId: Long): List<Questions>

    fun updateListQuestions(questionsListId: List<String>, answers: Answers)
}