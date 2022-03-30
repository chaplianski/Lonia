package com.example.testtask.domain.model

data class BriefCase(
    val briefCaseId: Int,
    val dateOfCreation: Long,
    var inspector: String = "",
    var port: String,
    var inspectorName: String = "",
    var inspectorType: String,
    var vessel: String = "",
    var category: String = "",
    var question: String

)
