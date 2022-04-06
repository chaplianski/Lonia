package com.example.testtask.data.repository

import com.example.testtask.data.storage.database.AnswersStorageImpl
import com.example.testtask.domain.model.Answers
import com.example.testtask.domain.repository.AnswersRepository
import javax.inject.Inject

class AnswersRepositoryImpl @Inject constructor(private val answersStorage: AnswersStorageImpl): AnswersRepository {

    override fun getAllAnswers(briefcaseId: Long): List<Answers> {
        val answersData = answersStorage.getAllAnswers(briefcaseId)
        val allAnswers = mutableListOf<Answers>()
        for (i in answersData) {
            allAnswers.add(Mapper.answersMapDataToDomain(i))
        }
        return allAnswers.toList()
    }

    override fun getAnswer(id: Int): Answers {
        TODO("Not yet implemented")
    }

    override fun addAnswer(answers: Answers) {
        TODO("Not yet implemented")
    }
}