package com.example.testtask.domain.usecase

import com.example.testtask.domain.repository.InspectionTypeRepository

class GetInspectionTypeUseCase (private val inspectionTypeRepository: InspectionTypeRepository) {

    fun execute(){
        inspectionTypeRepository.getInspectionType()
    }
}