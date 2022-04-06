package com.example.testtask.domain.usecase

import com.example.testtask.domain.model.Answers
import com.example.testtask.domain.repository.AnswersRepository
import com.example.testtask.domain.repository.BriefCaseRepository
import javax.inject.Inject

class AddAnswerUseCase @Inject constructor(private val answersRepository: AnswersRepository){

    fun execute (answer: Answers){
        answersRepository.addAnswer(answer)
    }
}