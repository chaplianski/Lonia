package com.example.testtask.domain.usecase

import com.example.testtask.domain.repository.QuestionRepository

class GetQuestionsUseCase (private val questionRepository: QuestionRepository) {

    fun execute (questionId: Int){
        questionRepository.getQuestion(questionId)
    }
}