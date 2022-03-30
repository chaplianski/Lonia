package com.example.testtask.data.storage.database

import com.example.testtask.data.storage.InspectionTypeStorage

class InspectionTypeStorageImpl : InspectionTypeStorage {

    override fun getInspectionTypeList(): List<String> {
        return listOf(
            "Audit", "Vetting", "Attendance", "Dry Dock",
            "Site Visits", "Terminal Inspection", "Third Party Inspection",
            "STAR TANKER Inspection", "MultiType Inspection"
        )
    }
}