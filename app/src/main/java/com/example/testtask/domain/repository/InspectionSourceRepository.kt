package com.example.testtask.domain.repository

import com.example.testtask.domain.model.InspectionSource

interface InspectionSourceRepository {

    fun getInspectionSource(): List<String>
}