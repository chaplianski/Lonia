package com.example.testtask.data.repository

import com.example.testtask.domain.repository.InspectionSourceRepository
import com.example.testtask.domain.repository.InspectionTypeRepository

class InspectionTypeRepositoryImpl (private val inspectionSourceRepository:
                                    InspectionSourceRepository): InspectionTypeRepository {

    override fun getInspectionType(): List<String> {
        return inspectionSourceRepository.getInspectionSource()
    }
}