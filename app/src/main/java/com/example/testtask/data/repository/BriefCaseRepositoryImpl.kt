package com.example.testtask.data.repository

import com.example.testtask.data.storage.BriefCaseStorage
import com.example.testtask.data.storage.model.BriefCaseData
import com.example.testtask.domain.model.BriefCase
import com.example.testtask.domain.repository.BriefCaseRepository
import java.util.stream.Collector
import java.util.stream.Collectors

class BriefCaseRepositoryImpl (private val briefCaseStorage: BriefCaseStorage ): BriefCaseRepository {

    override fun addBriefCase(briefCase: BriefCase) {
        val briefCaseData = BriefCaseData(
            briefCaseId = briefCase.briefCaseId,
            dateOfCreation = briefCase.dateOfCreation,
            inspector = briefCase.inspector,
            port = briefCase.port,
            inspectorName = briefCase.inspectorName,
            inspectorType = briefCase.inspectorType,
            vessel = briefCase.vessel,
            category = briefCase.category,
            question = briefCase.question
        )
        briefCaseStorage.addBriefCaseData(briefCaseData)
    }

    override fun getAllBriefCase(): List<BriefCase> {
        val allBriefCasesData = briefCaseStorage.getAllBriefCase()
        val  allBriefCases = mutableListOf<BriefCase>()
        for (i in allBriefCasesData){
            allBriefCases.add(mapDataToDomain(i))
        }
        return allBriefCases.toList()
    }

    override fun getBriefCase(briefCaseId: Int): BriefCase {
        val briefCaseData = briefCaseStorage.getBriefCase(briefCaseId)
        return mapDataToDomain(briefCaseData)
    }

    fun  mapDataToDomain (briefCaseData: BriefCaseData): BriefCase{
        return  BriefCase(
            briefCaseId = briefCaseData.briefCaseId,
            dateOfCreation = briefCaseData.dateOfCreation,
            inspector = briefCaseData.inspector,
            port = briefCaseData.port,
            inspectorName = briefCaseData.inspectorName,
            inspectorType = briefCaseData.inspectorType,
            vessel = briefCaseData.vessel,
            category = briefCaseData.category,
            question = briefCaseData.question
        )
    }




}