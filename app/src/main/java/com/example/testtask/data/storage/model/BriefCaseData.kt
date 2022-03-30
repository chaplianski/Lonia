package com.example.testtask.data.storage.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.testtask.domain.model.Questions

@Entity(tableName = "briefcase")
data class BriefCaseData(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "briefCaseId")
    val briefCaseId: Int,
    @ColumnInfo(name = "dateOfCreation")
    val dateOfCreation: Long,
    @ColumnInfo(name = "inspector")
    val inspector: String = "",
    @ColumnInfo(name = "port")
    val port: String,
    @ColumnInfo(name = "inspectorName")
    val inspectorName: String = "",
    @ColumnInfo(name = "inspectorType")
    val inspectorType: String,
    @ColumnInfo(name = "vessel")
    val vessel: String = "",
    @ColumnInfo(name = "category")
    val category: String = "",
    @ColumnInfo(name = "question")
    val question: String

)
