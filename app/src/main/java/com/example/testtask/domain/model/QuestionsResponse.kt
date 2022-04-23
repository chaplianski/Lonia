package com.example.testtask.domain.model

data class QuestionsResponse(
    var response: List<Questions>? = emptyList(),
    val status: String = "",

)
