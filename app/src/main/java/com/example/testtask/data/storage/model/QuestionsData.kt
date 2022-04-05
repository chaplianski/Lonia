package com.example.testtask.data.storage.model

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity
data class QuestionsData(
    @PrimaryKey(autoGenerate = true)
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
