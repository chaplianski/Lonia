package com.example.lonia.data.repository

import com.example.lonia.data.storage.database.AnswersStorageImpl
import com.example.lonia.domain.model.Answers
import com.example.lonia.domain.model.Questions
import com.example.lonia.domain.repository.AnswersRepository
import javax.inject.Inject

class AnswersRepositoryImpl @Inject constructor(private val answersStorage: AnswersStorageImpl) :
    AnswersRepository {

    override fun getAnsweredQuestions(briefcaseId: Long): List<Questions> {
        return answersStorage.getAnsweredQuestions(briefcaseId)
            .map { it.questionsMapDataToDomain() }
    }

    override fun getAnswer(answerId: Long): Answers {
        val answerData = answersStorage.getAnswer(answerId)
        return answerData.answersMapDataToDomain()
    }

    override fun updateAnswer(answers: Answers) {
        val answerData = answers.answersMapDomainToData()
        answersStorage.updatedAnswer(answerData)
    }
}