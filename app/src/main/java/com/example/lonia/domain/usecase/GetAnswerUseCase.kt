package com.example.lonia.domain.usecase

import com.example.lonia.domain.model.Answers
import com.example.lonia.domain.repository.AnswersRepository
import javax.inject.Inject

class GetAnswerUseCase @Inject constructor(private val answersRepository: AnswersRepository){

//    fun execute (idAnswer: Long): Answers {
//        return answersRepository.getAnswer(idAnswer)
//    }
}