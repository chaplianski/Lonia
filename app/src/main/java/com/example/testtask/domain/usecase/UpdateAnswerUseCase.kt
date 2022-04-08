package com.example.testtask.domain.usecase

import com.example.testtask.domain.model.Answers
import com.example.testtask.domain.repository.AnswersRepository
import javax.inject.Inject

class UpdateAnswerUseCase @Inject constructor(private val answersRepository: AnswersRepository) {

    fun execute (answers: Answers){
        answersRepository.updateAnswer(answers)
    }
}