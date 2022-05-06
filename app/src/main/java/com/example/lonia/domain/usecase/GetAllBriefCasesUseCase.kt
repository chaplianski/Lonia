package com.example.lonia.domain.usecase

import com.example.lonia.domain.model.BriefCase
import com.example.lonia.domain.repository.BriefCaseRepository
import javax.inject.Inject

class GetAllBriefCasesUseCase @Inject constructor(private val briefCaseRepository: BriefCaseRepository) {

    fun execute(): List<BriefCase>{
        return briefCaseRepository.getAllBriefCase()
    }
}