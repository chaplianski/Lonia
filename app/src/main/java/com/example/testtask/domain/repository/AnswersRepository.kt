package com.example.testtask.domain.repository

import com.example.testtask.domain.model.Answers

interface AnswersRepository {

    fun getAllAnswers(briefcaseId: Long): List<Answers>

    fun getAnswer (id: Int): Answers

    fun addAnswer (answers: Answers)
}