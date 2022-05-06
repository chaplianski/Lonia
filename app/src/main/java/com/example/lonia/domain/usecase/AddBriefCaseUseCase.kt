package com.example.lonia.domain.usecase

import com.example.lonia.domain.model.BriefCase
import com.example.lonia.domain.model.Questions
import com.example.lonia.domain.repository.BriefCaseRepository
import javax.inject.Inject

class AddBriefCaseUseCase @Inject constructor (private val briefCaseRepository: BriefCaseRepository) {

    fun execute (briefcase: BriefCase, listQuestions: List<Questions>){
        briefCaseRepository.addBriefCase(briefcase, listQuestions)
    }
}