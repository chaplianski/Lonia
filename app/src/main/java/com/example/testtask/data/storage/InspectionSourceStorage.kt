package com.example.testtask.data.storage

import com.example.testtask.data.storage.model.InspectorSourceData

interface InspectionSourceStorage {

    fun getInspectorSource(): List<String>
}