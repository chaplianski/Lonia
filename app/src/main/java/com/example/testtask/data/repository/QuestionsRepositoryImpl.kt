package com.example.testtask.data.repository

import com.example.testtask.data.storage.database.QuestionsStorageImpl
import com.example.testtask.data.storage.network.retrofit.QuestionsApiHelper
import com.example.testtask.domain.model.Answers
import com.example.testtask.domain.model.Questions
import com.example.testtask.domain.repository.QuestionsRepository
import javax.inject.Inject

class QuestionsRepositoryImpl @Inject constructor(
    private val questionsStorage: QuestionsStorageImpl,
    private val questionsApiHelper: QuestionsApiHelper
) : QuestionsRepository {

    override fun getQuestions(briefcaseId: Long): List<Questions> {
        return questionsStorage.getQuestionsList(briefcaseId).map { it.questionsMapDataToDomain() }
    }

    override suspend fun fetchQuestions(qid: Int): List<Questions> {
        return questionsApiHelper.getQuestions(qid).map { it.questionsMapDataToDomain() }
    }

    override fun updateQuestions(questions: Questions, answers: Answers) {
        val questionsData = questions.questionsMapDomainToData()
        val answersData = answers.answersMapDomainToData()
        questionsStorage.updateQuestions(questionsData, answersData)
    }

    override fun getNotAnsweredQuestions(briefcaseId: Long): List<Questions> {
        return questionsStorage.getNotAnsweredQuestionsList(briefcaseId).map { it.questionsMapDataToDomain() }
    }

    override fun updateListQuestions(questionsListId: List<String>, answers: Answers) {
        questionsStorage.updateQuestionsListAddAnswer(questionsListId, answers.answersMapDomainToData())
    }
}