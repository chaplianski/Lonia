package com.example.lonia.domain.repository

interface InspectionSourceRepository {

    suspend fun getInspectionSource(): List<String>
}