package com.example.lonia.domain.repository

import com.example.lonia.domain.model.Answers
import com.example.lonia.domain.model.Questions

interface AnswersRepository {

    fun getAnsweredQuestions(briefcaseId: Long): List<Questions>

//    fun getAnswer (idAnswer: Long): Answers
//
//    fun updateAnswer (answers: Answers)
}