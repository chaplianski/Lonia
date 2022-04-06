package com.example.testtask.domain.usecase

import com.example.testtask.domain.model.Answers
import com.example.testtask.domain.model.Questions
import com.example.testtask.domain.repository.QuestionsRepository
import javax.inject.Inject

class UpdateQuestionsUseCase @Inject constructor(private val questionsRepository: QuestionsRepository){

    fun execute(questions: Questions, answers: Answers){
        questionsRepository.updateQuestions(questions,answers)
    }
}