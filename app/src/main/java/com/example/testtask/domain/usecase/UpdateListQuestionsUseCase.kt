package com.example.testtask.domain.usecase

import com.example.testtask.domain.model.Answers
import com.example.testtask.domain.repository.QuestionsRepository
import javax.inject.Inject

class UpdateListQuestionsUseCase @Inject constructor(private val questionsRepository: QuestionsRepository) {

    fun execute (listQuestionsId: List<String>, answer: Answers){
        questionsRepository.updateListQuestions(listQuestionsId, answer)
    }

}