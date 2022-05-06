package com.example.lonia.domain.usecase

import com.example.lonia.domain.model.Questions
import com.example.lonia.domain.repository.QuestionsRepository
import javax.inject.Inject

class UpdateQuestionUseCase @Inject constructor(private val questionsRepository: QuestionsRepository) {

    fun execute(question: Questions){
        questionsRepository.updateQuestion(question)
    }
}