package com.example.testtask.domain.usecase

import com.example.testtask.domain.model.BriefCase
import com.example.testtask.domain.repository.BriefCaseRepository

class AddBriefCaseUseCase (private val briefCaseRepository: BriefCaseRepository) {

    fun execute (briefCase: BriefCase){
        briefCaseRepository.addBriefCase(briefCase)
    }
}