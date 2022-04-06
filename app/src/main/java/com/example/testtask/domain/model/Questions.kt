package com.example.testtask.domain.model

import com.squareup.moshi.JsonClass


@JsonClass(generateAdapter = true)
data class Questions(
 //   val idQuestion: Int = 0,
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
    val briefCaseId: Long
    )
