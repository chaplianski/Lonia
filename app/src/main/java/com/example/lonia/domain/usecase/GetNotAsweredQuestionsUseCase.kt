package com.example.lonia.domain.usecase

import com.example.lonia.domain.model.Questions
import com.example.lonia.domain.repository.QuestionsRepository
import javax.inject.Inject

class GetNotAsweredQuestionsUseCase @Inject constructor(private val questionsRepository: QuestionsRepository) {

    fun execute(briefCaseId: Long): List<Questions>{
        return questionsRepository.getNotAnsweredQuestions(briefCaseId)
    }
}