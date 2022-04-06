package com.example.testtask.domain.usecase

import com.example.testtask.data.storage.model.BriefcaseWithQuestions
import com.example.testtask.domain.model.BriefCase
import com.example.testtask.domain.model.Questions
import com.example.testtask.domain.repository.BriefCaseRepository
import javax.inject.Inject

class AddBriefCaseUseCase @Inject constructor (private val briefCaseRepository: BriefCaseRepository) {

    fun execute (briefcase: BriefCase, listQuestions: List<Questions>){
        briefCaseRepository.addBriefCase(briefcase, listQuestions)
    }
}