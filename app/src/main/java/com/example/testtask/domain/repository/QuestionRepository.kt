package com.example.testtask.domain.repository

import com.example.testtask.domain.model.Questions

interface QuestionRepository {

    fun getQuestion (questionId: Int)

    fun addQuestion (question: Questions)
}