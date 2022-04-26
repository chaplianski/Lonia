package com.example.testtask.data.storage.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity (tableName = "answers")
data class AnswersData(
    @PrimaryKey(autoGenerate = true)
    val answerId: Long = 0,
    val answer: String = "",
    val answerData: Long = 0L,
    val answerChoice: Boolean = true,

)
