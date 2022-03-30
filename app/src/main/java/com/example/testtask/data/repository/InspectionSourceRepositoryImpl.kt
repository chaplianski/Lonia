package com.example.testtask.data.repository

import com.example.testtask.data.storage.InspectionSourceStorage
import com.example.testtask.domain.repository.InspectionSourceRepository

class InspectionSourceRepositoryImpl (private val inspectorSourceStorage: InspectionSourceStorage) : InspectionSourceRepository {

    override fun getInspectionSource(): List<String> {
        return inspectorSourceStorage.getInspectorSource()
    }
}