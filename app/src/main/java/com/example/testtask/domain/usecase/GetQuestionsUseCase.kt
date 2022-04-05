package com.example.testtask.domain.usecase

import com.example.testtask.domain.model.Questions
import com.example.testtask.domain.repository.QuestionsRepository
import javax.inject.Inject

class GetQuestionsUseCase @Inject constructor (private val questionRepository: QuestionsRepository) {

    fun execute (): List<Questions>{
        return questionRepository.getQuestions()
    }
}