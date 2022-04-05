package com.example.testtask.data.repository

import com.example.testtask.data.storage.storagies.InspectionSourceStorage
import com.example.testtask.domain.repository.InspectionSourceRepository
import javax.inject.Inject

class InspectionSourceRepositoryImpl @Inject constructor(private val inspectorSourceStorage: InspectionSourceStorage) : InspectionSourceRepository {

    override fun getInspectionSource(): List<String> {
        return inspectorSourceStorage.getInspectorSource()
    }
}