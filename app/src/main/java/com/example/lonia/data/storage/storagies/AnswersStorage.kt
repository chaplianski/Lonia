package com.example.lonia.data.storage.storagies

import com.example.lonia.data.storage.model.AnswersData
import com.example.lonia.data.storage.model.QuestionsData

interface AnswersStorage {

    fun getAnsweredQuestions(briefcaseId: Long): List<QuestionsData>

    fun updatedAnswer(answersData: AnswersData)

    fun getAnswer (answerId: Long): AnswersData
}