package com.example.testtask.domain.usecase

import com.example.testtask.domain.repository.InspectionSourceRepository
import javax.inject.Inject

class GetInspectionSourceUseCase @Inject constructor(private val inspectionSourceRepository: InspectionSourceRepository){

    fun execute (): List<String> {
        return inspectionSourceRepository.getInspectionSource()
    }
}