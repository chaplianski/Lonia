package com.example.testtask.domain.usecase

import com.example.testtask.domain.model.Answers
import com.example.testtask.domain.repository.AnswersRepository
import com.example.testtask.domain.repository.BriefCaseRepository

class AddAnswerUseCase (private val answersRepository: AnswersRepository){

    fun execute (answer: Answers){
        answersRepository.addAnswer(answer)
    }
}