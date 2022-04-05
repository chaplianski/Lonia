package com.example.testtask.data.repository

import com.example.testtask.data.storage.database.QuestionsStorageImpl
import com.example.testtask.data.storage.network.retrofit.NetQuestionsRepository
import com.example.testtask.domain.model.Questions
import com.example.testtask.domain.repository.QuestionsRepository
import javax.inject.Inject

class QuestionsRepositoryImpl @Inject constructor(
    private val questionsStorage: QuestionsStorageImpl,
    private val netQuestionsRepository: NetQuestionsRepository
) : QuestionsRepository {

    override fun getQuestions(): List<Questions> {
        TODO("Not yet implemented")
    }

    override fun addQuestions(question: Questions) {
        val questionsData = Mapper.questionsMapDomainToData(question)
        questionsStorage.addAllQuestions(questionsData)
    }

    override suspend fun fetchQuestions(qid: Int): List<Questions> {
        val questionsLDataList = netQuestionsRepository.getQuestions(qid)
        val questionsList = mutableListOf<Questions>()
        for (i in questionsLDataList) {
            questionsList.add(Mapper.questionsMapDataToDomain(i))
        }
        return questionsList.toList()


    }
}