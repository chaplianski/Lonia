package com.example.testtask.domain.model

data class BriefcasesAndQuestions(
    val briefCaseId: Long,
    val dateOfCreation: Long,
    var inspector: String = "",
    var port: String ="",
    var inspectorName: String = "",
    var inspectorType: String = "",
    var vessel: String = "",
    var category: String = "",
    var answered: Int = 0,
    val total: Int =9

)
