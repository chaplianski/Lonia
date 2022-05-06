package com.example.lonia.data.storage.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "notes")
data class NotesData(
    @PrimaryKey(autoGenerate = true)
    val noteId: Long,
    val noteValue: String,
    val briefcaseId: Long,
    val noteName: String
)
