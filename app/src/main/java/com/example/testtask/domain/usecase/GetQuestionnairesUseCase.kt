package com.example.testtask.domain.usecase

import com.example.testtask.domain.model.Questionnaires
import com.example.testtask.domain.repository.QuestionnairesRepository
import javax.inject.Inject

class GetQuestionnairesUseCase @Inject constructor(private val questionnairesRepository: QuestionnairesRepository) {

    suspend fun execute (): List<Questionnaires>{
        return questionnairesRepository.getQuestionnaires()
    }
}