package com.example.testtask.domain.usecase

import com.example.testtask.domain.repository.InspectionTypeRepository
import javax.inject.Inject

class GetInspectionTypeUseCase @Inject constructor (private val inspectionTypeRepository: InspectionTypeRepository) {

    fun execute(): List<String>{
        return inspectionTypeRepository.getInspectionType()
    }
}