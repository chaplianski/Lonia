package com.example.lonia.domain.usecase

import com.example.lonia.domain.model.Answers
import com.example.lonia.domain.repository.QuestionsRepository
import javax.inject.Inject

class UpdateListQuestionsUseCase @Inject constructor(private val questionsRepository: QuestionsRepository) {

    fun execute (listQuestionsId: List<String>, answer: Answers){
        questionsRepository.updateListQuestions(listQuestionsId, answer)
    }

}