package com.example.lonia.data.storage.storagies

import com.example.lonia.data.storage.model.AnswersData
import com.example.lonia.data.storage.model.QuestionsData

interface QuestionsStorage {

    fun getQuestionsList(briefcaseId: Long): List<QuestionsData>

    fun updateQuestions(questionsData: QuestionsData, answersData: AnswersData)

    fun getNotAnsweredQuestionsList(briefcaseId: Long): List<QuestionsData>

    fun updateQuestionsListAddAnswer(questionsList: List<String>, answer: AnswersData)

    fun updateQuestion(questionsData: QuestionsData)

    fun getQuestion(questionId: String): QuestionsData
}