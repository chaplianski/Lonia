package com.example.testtask.domain.usecase

import com.example.testtask.domain.model.Questions
import com.example.testtask.domain.repository.QuestionsRepository
import javax.inject.Inject

class GetQuestionUseCase @Inject constructor(private val questionsRepository: QuestionsRepository) {

    fun execute(questionId: String): Questions{
        return questionsRepository.getQuestion(questionId)
    }
}