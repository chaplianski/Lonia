package com.example.testtask.data.storage.model

import com.example.testtask.domain.model.Questionnaires

data class QuestionnairesDataResponse(
    var response : List<QuestionnairesData>? = emptyList(),
    val status: String = "",


)
