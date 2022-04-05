package com.example.testtask.domain.usecase

import com.example.testtask.domain.model.Questions
import com.example.testtask.domain.repository.QuestionsRepository

class AddQuestionsUseCase (private val questionRepository: QuestionsRepository) {

    fun execute (questions: Questions){
        questionRepository.addQuestions(questions)
    }
}