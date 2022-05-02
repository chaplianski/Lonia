package com.example.testtask.domain.usecase

import com.example.testtask.domain.model.Questions
import com.example.testtask.domain.repository.QuestionsRepository
import javax.inject.Inject

class GetNotAsweredQuestionsUseCase @Inject constructor(private val questionsRepository: QuestionsRepository) {

    fun execute(briefCaseId: Long): List<Questions>{
        return questionsRepository.getNotAnsweredQuestions(briefCaseId)
    }
}