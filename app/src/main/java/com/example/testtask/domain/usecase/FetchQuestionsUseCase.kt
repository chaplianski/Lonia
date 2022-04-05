package com.example.testtask.domain.usecase

import com.example.testtask.domain.model.Questions
import com.example.testtask.domain.repository.QuestionsRepository
import javax.inject.Inject

class FetchQuestionsUseCase @Inject constructor(private val questionsRepository: QuestionsRepository) {

    suspend fun execute(qid: Int): List<Questions>{
        return questionsRepository.fetchQuestions(qid)
    }
}