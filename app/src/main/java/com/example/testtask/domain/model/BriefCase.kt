package com.example.testtask.domain.model

import androidx.room.PrimaryKey

data class BriefCase(
    @PrimaryKey(autoGenerate = true)
    val briefCaseId: Long,
    val dateOfCreation: Long,
    var inspector: String = "",
    var port: String,
    var inspectorName: String = "",
    var inspectorType: String,
    var vessel: String = "",
    var category: String = "",

)
