package com.example.testtask.data.storage.storagies

import com.example.testtask.data.storage.model.AnswersData
import com.example.testtask.data.storage.model.QuestionsData

interface QuestionsStorage {

    fun getQuestionsList(briefcaseId: Long): List<QuestionsData>

    fun updateQuestions(questionsData: QuestionsData, answersData: AnswersData)

}