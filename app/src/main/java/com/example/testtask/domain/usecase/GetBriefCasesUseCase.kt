package com.example.testtask.domain.usecase

import com.example.testtask.domain.repository.BriefCaseRepository

class GetBriefCasesUseCase (private val briefCaseRepository: BriefCaseRepository){

    fun execute (id: Int){
        briefCaseRepository.getBriefCase(id)
    }
}