package com.example.lonia.domain.repository

import com.example.lonia.domain.model.Answers
import com.example.lonia.domain.model.Questions

interface QuestionsRepository {

    fun getQuestions (briefcaseId: Long): List<Questions>

    suspend fun fetchQuestions (qid: Int): List<Questions>

//    fun updateQuestions(questions: Questions, answers: Answers)

    fun getNotAnsweredQuestions(briefcaseId: Long): List<Questions>

    fun updateListQuestions(questionsListId: List<String>, answers: Answers)

    fun updateQuestion(question: Questions)

    fun getQuestion(questionId: String): Questions
}