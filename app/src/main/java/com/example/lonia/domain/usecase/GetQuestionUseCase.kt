package com.example.lonia.domain.usecase

import com.example.lonia.domain.model.Questions
import com.example.lonia.domain.repository.QuestionsRepository
import javax.inject.Inject

class GetQuestionUseCase @Inject constructor(private val questionsRepository: QuestionsRepository) {

    fun execute(questionId: String): Questions {
        return questionsRepository.getQuestion(questionId)
    }
}