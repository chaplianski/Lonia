package com.example.lonia.domain.repository

interface InspectionTypeRepository {

    suspend fun getInspectionType(): List<String>
}