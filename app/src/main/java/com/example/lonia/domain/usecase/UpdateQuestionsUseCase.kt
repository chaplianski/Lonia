package com.example.lonia.domain.usecase

import com.example.lonia.domain.model.Answers
import com.example.lonia.domain.model.Questions
import com.example.lonia.domain.repository.QuestionsRepository
import javax.inject.Inject

class UpdateQuestionsUseCase @Inject constructor(private val questionsRepository: QuestionsRepository){

    fun execute(questions: Questions, answers: Answers){
        questionsRepository.updateQuestions(questions,answers)
    }
}