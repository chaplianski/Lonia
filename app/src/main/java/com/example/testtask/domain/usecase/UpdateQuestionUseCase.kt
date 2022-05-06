package com.example.testtask.domain.usecase

import com.example.testtask.domain.model.Questions
import com.example.testtask.domain.repository.QuestionsRepository
import javax.inject.Inject

class UpdateQuestionUseCase @Inject constructor(private val questionsRepository: QuestionsRepository) {

    fun execute(question: Questions){
        questionsRepository.updateQuestion(question)
    }
}