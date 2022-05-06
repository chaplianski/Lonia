package com.example.lonia.domain.usecase

import com.example.lonia.domain.model.Questions
import com.example.lonia.domain.repository.QuestionsRepository
import javax.inject.Inject

class GetQuestionsUseCase @Inject constructor (private val questionRepository: QuestionsRepository) {

    fun execute (briefcseId: Long): List<Questions>{
        return questionRepository.getQuestions(briefcseId)
    }


}