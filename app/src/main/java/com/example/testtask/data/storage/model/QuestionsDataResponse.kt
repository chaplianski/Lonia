package com.example.testtask.data.storage.model

import com.example.testtask.domain.model.Questions

data class QuestionsDataResponse(
    var response: List<QuestionsData>? = emptyList(),
    val status: String = "",
)