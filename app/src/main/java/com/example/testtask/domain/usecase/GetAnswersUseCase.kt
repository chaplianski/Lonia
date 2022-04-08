package com.example.testtask.domain.usecase

import com.example.testtask.domain.model.Questions
import com.example.testtask.domain.repository.AnswersRepository
import javax.inject.Inject

class GetAnswersUseCase @Inject constructor(private val answersRepository: AnswersRepository) {

    fun execute(briefcaseId: Long): List<Questions>{
        return answersRepository.getAnsweredQuestions(briefcaseId)
    }
}