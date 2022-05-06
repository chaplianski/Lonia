package com.example.lonia.domain.usecase

import com.example.lonia.domain.repository.InspectionSourceRepository
import javax.inject.Inject

class GetInspectionSourceUseCase @Inject constructor(private val inspectionSourceRepository: InspectionSourceRepository){

    fun execute (): List<String> {
        return inspectionSourceRepository.getInspectionSource()
    }
}