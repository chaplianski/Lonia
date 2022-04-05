package com.example.testtask.data.storage.storagies

import com.example.testtask.data.storage.model.QuestionsData

interface QuestionsStorage {

    fun getQuestionsList(): List<QuestionsData>

    fun addAllQuestions(questionsData: QuestionsData)

}