package com.example.testtask.domain.model

import com.squareup.moshi.JsonClass


@JsonClass(generateAdapter = true)
data class Questions(
    val idQuestion: Int = 0,
    var questionID: String,
    val comment: String,
    val dateOfInspection: String = "",
    val answer: Int = 0,
    val questionCode: String = "",
    val question: String = "",
    val commentForQuestion: String = "",
    val categoryID: String,
    val origin: String = "",
    val categoryNewID: String = "",
    val isAnswered: Boolean = false,
    val images: Int? = null,
    val briefCaseId: Int
    )
