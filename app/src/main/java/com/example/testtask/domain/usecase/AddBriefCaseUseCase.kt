package com.example.testtask.domain.usecase

import com.example.testtask.domain.model.BriefCase
import com.example.testtask.domain.repository.BriefCaseRepository
import javax.inject.Inject

class AddBriefCaseUseCase @Inject constructor (private val briefCaseRepository: BriefCaseRepository) {

    fun execute (briefCase: BriefCase){
        briefCaseRepository.addBriefCase(briefCase)
    }
}