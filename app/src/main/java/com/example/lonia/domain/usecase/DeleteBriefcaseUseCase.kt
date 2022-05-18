package com.example.lonia.domain.usecase

import com.example.lonia.domain.repository.BriefCaseRepository
import javax.inject.Inject

class DeleteBriefcaseUseCase @Inject constructor(private val briefCaseRepository: BriefCaseRepository) {

    fun execute (briefcaseId: Long){
        briefCaseRepository.deleteBriefcase(briefcaseId)
    }
}