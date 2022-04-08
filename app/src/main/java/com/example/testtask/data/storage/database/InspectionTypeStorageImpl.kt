package com.example.testtask.data.storage.database

import com.example.testtask.data.storage.storagies.InspectionTypeStorage
import javax.inject.Inject

class InspectionTypeStorageImpl @Inject constructor() : InspectionTypeStorage {

    override fun getInspectionTypeList(): List<String> {
        return listOf(
            "Audit", "Vetting", "Attendance", "Dry Dock",
            "Site Visits", "Terminal Inspection", "Third Party Inspection",
            "STAR TANKER Inspection", "MultiType Inspection"
        )
    }
}