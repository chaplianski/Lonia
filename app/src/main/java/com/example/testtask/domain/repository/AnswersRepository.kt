package com.example.testtask.domain.repository

import com.example.testtask.domain.model.Answers
import com.example.testtask.domain.model.Questions

interface AnswersRepository {

    fun getAnsweredQuestions(briefcaseId: Long): List<Questions>

    fun getAnswer (idAnswer: Long): Answers

    fun updateAnswer (answers: Answers)
}