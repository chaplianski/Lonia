package com.example.lonia.domain.usecase

import com.example.lonia.domain.model.Questions
import com.example.lonia.domain.repository.AnswersRepository
import javax.inject.Inject

class GetAnswersUseCase @Inject constructor(private val answersRepository: AnswersRepository) {

    fun execute(briefcaseId: Long): List<Questions>{
        return answersRepository.getAnsweredQuestions(briefcaseId)
    }
}