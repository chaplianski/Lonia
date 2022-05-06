package com.example.lonia.domain.usecase

import com.example.lonia.domain.repository.InspectionTypeRepository
import javax.inject.Inject

class GetInspectionTypeUseCase @Inject constructor (private val inspectionTypeRepository: InspectionTypeRepository) {

    fun execute(): List<String>{
        return inspectionTypeRepository.getInspectionType()
    }
}