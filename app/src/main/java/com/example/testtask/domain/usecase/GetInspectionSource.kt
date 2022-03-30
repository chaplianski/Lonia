package com.example.testtask.domain.usecase

import com.example.testtask.domain.repository.InspectionSourceRepository

class GetInspectionSource (private val inspectionSourceRepository: InspectionSourceRepository){

    fun execute (){
        inspectionSourceRepository.getInspectionSource()
    }
}