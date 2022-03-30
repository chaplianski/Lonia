package com.example.testtask.domain.usecase

import com.example.testtask.domain.model.BriefCase
import com.example.testtask.domain.repository.BriefCaseRepository

class GetAllBriefCases (private val briefCaseRepository: BriefCaseRepository) {

    fun execute(): List<BriefCase>{
        return briefCaseRepository.getAllBriefCase()
    }
}