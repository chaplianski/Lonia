package com.example.testtask.data.repository

import android.util.Log
import com.example.testtask.data.storage.database.QuestionsStorageImpl
import com.example.testtask.data.storage.network.retrofit.NetQuestionsRepository
import com.example.testtask.domain.model.Answers
import com.example.testtask.domain.model.Questions
import com.example.testtask.domain.model.QuestionsResponse
import com.example.testtask.domain.repository.QuestionsRepository
import javax.inject.Inject

class QuestionsRepositoryImpl @Inject constructor(
    private val questionsStorage: QuestionsStorageImpl,
    private val netQuestionsRepository: NetQuestionsRepository
) : QuestionsRepository {

    override fun getQuestions(briefcaseId: Long): List<Questions> {
        return questionsStorage.getQuestionsList(briefcaseId).map { it.questionsMapDataToDomain() }
    }

    override suspend fun fetchQuestions(qid: Int): QuestionsResponse {
        val questionsResponse = netQuestionsRepository.getQuestions(qid)
        return QuestionsResponse(
            questionsResponse.response?.map {it.questionsMapDataToDomain()},
            questionsResponse.status
        )

    //    val questionsResponse = netQuestionsRepository.getQuestions(qid).response?.map { it.questionsMapDataToDomain() }

    }

 //   override suspend fun fetchQuestions(qid: Int): List<Questions> {
//        return netQuestionsRepository.getQuestions(qid).map { it.questionsMapDataToDomain() }
 //   }



    override fun updateQuestions(questions: Questions, answers: Answers) {
        val questionsData = questions.questionsMapDomainToData()
        val answersData = answers.answersMapDomainToData()
        questionsStorage.updateQuestions(questionsData, answersData)
    }
}