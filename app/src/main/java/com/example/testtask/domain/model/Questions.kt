package com.example.testtask.domain.model

data class Questions(
    var questionID: String,
    val comment: String,
    val dateOfInspection: String = "",
    val answer: Int,
    val questionCode: String = "",
    val question: String = "",
    val commentForQuestion: String = "",
    val categoryID: String = "",
    val origin: String = "",
    val categoryNewID: String = "",
    val isAnswered: Boolean = false,
    val images: Int

    )
