package com.example.testtask.domain.model

data class QuestionnariesResponse (
    var response : List<Questionnaires>? = emptyList(),
    val status: String = "",
)