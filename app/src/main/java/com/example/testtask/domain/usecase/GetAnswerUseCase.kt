package com.example.testtask.domain.usecase

import com.example.testtask.domain.model.Answers
import com.example.testtask.domain.repository.AnswersRepository
import javax.inject.Inject

class GetAnswerUseCase @Inject constructor(private val answersRepository: AnswersRepository){

    fun execute (id: Int): Answers{
        return answersRepository.getAnswer(id)
    }
}