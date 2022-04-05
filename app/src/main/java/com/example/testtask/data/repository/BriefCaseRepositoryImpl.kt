package com.example.testtask.data.repository

import com.example.testtask.data.storage.storagies.BriefCaseStorage
import com.example.testtask.data.storage.model.BriefCaseData
import com.example.testtask.domain.model.BriefCase
import com.example.testtask.domain.repository.BriefCaseRepository
import javax.inject.Inject

class BriefCaseRepositoryImpl @Inject constructor(private val briefCaseStorage: BriefCaseStorage) :
    BriefCaseRepository {

    override fun addBriefCase(briefCase: BriefCase) {
        val briefCaseData = Mapper.briefcaseMapDomainToData(briefCase)
        briefCaseStorage.addBriefCaseData(briefCaseData)
    }

    override fun getAllBriefCase(): List<BriefCase> {
        val allBriefCasesData = briefCaseStorage.getAllBriefCase()
        val allBriefCases = mutableListOf<BriefCase>()
        for (i in allBriefCasesData) {
            allBriefCases.add(Mapper.briefcaseMapDataToDomain(i))
        }
        return allBriefCases.toList()
    }

    override fun getBriefCase(briefCaseId: Int): BriefCase {
        val briefCaseData = briefCaseStorage.getBriefCase(briefCaseId)
        return Mapper.briefcaseMapDataToDomain(briefCaseData)
    }
}