package com.example.lonia.data.repository

import com.example.lonia.data.storage.storagies.BriefCaseStorage
import com.example.lonia.domain.model.BriefCase
import com.example.lonia.domain.model.Questions
import com.example.lonia.domain.repository.BriefCaseRepository
import javax.inject.Inject

class BriefCaseRepositoryImpl @Inject constructor(private val briefCaseStorage: BriefCaseStorage) :
    BriefCaseRepository {

    override fun getAllBriefCase(): List<BriefCase> {
        return briefCaseStorage.getAllBriefCase()
            .map { it.briefcaseMapDataToDomain() } //allBriefCases.toList()
    }

    override fun addBriefCase(briefcase: BriefCase, listQuestions: List<Questions>) {
        val briefCaseData = briefcase.briefcaseMapDomainToData()
        val listQuestionsData = listQuestions.map { it.questionsMapDomainToData() }
        return briefCaseStorage.addBriefCaseData(briefCaseData, listQuestionsData)
    }
}