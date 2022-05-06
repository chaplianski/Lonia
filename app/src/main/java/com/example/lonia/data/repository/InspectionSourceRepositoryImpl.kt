package com.example.lonia.data.repository

import com.example.lonia.data.storage.storagies.InspectionSourceStorage
import com.example.lonia.domain.repository.InspectionSourceRepository
import javax.inject.Inject

class InspectionSourceRepositoryImpl @Inject constructor(private val inspectorSourceStorage: InspectionSourceStorage) :
    InspectionSourceRepository {

    override fun getInspectionSource(): List<String> {
        return inspectorSourceStorage.getInspectorSource()
    }
}