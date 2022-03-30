package com.example.testtask.domain.usecase

import com.example.testtask.domain.model.Questions
import com.example.testtask.domain.repository.QuestionRepository

class AddQuestionUseCase (private val questionRepository: QuestionRepository) {

    fun execute (question: Questions){
        questionRepository.addQuestion(question)
    }
}