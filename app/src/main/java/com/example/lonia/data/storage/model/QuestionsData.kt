package com.example.lonia.data.storage.model

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "questions")
data class QuestionsData(
    @PrimaryKey(autoGenerate = false)
    var questionid: String = "",
    val comment: String = "",
    val dateOfInspection: String = "",
    val answer: Int = 0,
    val questioncode: String = "",
    val question: String = "",
    val commentForQuestion: String = "",
    val categoryid: String = "",
    val origin: String = "",
    val categorynewid: String = "",
    val isAnswered: Boolean = false,
    val images: Int? = null,
    var briefCaseId: Long = 0L,
    val significance: String = ""
)

