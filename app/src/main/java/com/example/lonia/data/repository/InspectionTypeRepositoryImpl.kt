package com.example.lonia.data.repository

import com.example.lonia.data.storage.database.InspectionTypeStorageImpl
import com.example.lonia.domain.repository.InspectionTypeRepository
import javax.inject.Inject

class InspectionTypeRepositoryImpl @Inject constructor(private val inspectionTypeStorage: InspectionTypeStorageImpl) :
    InspectionTypeRepository {

    override fun getInspectionType(): List<String> {
        return inspectionTypeStorage.getInspectionTypeList()
    }
}