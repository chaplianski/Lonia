package com.example.lonia.domain.model

import com.squareup.moshi.JsonClass


@JsonClass(generateAdapter = true)
data class Questions(
    var questionid: String,
    val comment: String,
    val dateOfInspection: String = "",
    val answer: Int = 0,
    val questioncode: String = "",
    val question: String = "",
    val commentForQuestion: String = "",
    val categoryid: String,
    val origin: String = "",
    val categorynewid: String = "",
    val isAnswered: Boolean = false,
    val images: Int? = null,
    val briefCaseId: Long,
    val significance: String = ""
)
