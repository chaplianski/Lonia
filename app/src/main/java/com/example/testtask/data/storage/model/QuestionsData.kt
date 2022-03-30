package com.example.testtask.data.storage.model

data class QuestionsData(
    var questionID: String,
    val comment: String,
    val dateOfInspection: String = "",
    val answer: Int = 0,
    val questionCode: String = "",
    val question: String = "",
    val commentForQuestion: String = "",
    val categoryID: String = "",
    val origin: String = "",
    val categoryNewID: String = "",
    val isAnswered: Boolean = false,
    val images: Int
)
