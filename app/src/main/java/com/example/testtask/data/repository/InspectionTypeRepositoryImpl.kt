package com.example.testtask.data.repository

import com.example.testtask.data.storage.database.InspectionTypeStorageImpl
import com.example.testtask.domain.repository.InspectionSourceRepository
import com.example.testtask.domain.repository.InspectionTypeRepository
import javax.inject.Inject

class InspectionTypeRepositoryImpl @Inject constructor(private val inspectionTypeStorage: InspectionTypeStorageImpl) :
    InspectionTypeRepository {

    override fun getInspectionType(): List<String> {
        return inspectionTypeStorage.getInspectionTypeList()
    }
}