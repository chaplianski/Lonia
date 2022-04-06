package com.example.testtask.data.storage.model

import androidx.room.*
import com.example.testtask.domain.model.Questions

@Entity(tableName = "briefcase")
data class BriefCaseData(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "briefCaseId")
    var briefCaseId: Long = 0,
    @ColumnInfo(name = "dateOfCreation")
    var dateOfCreation: Long ,
    @ColumnInfo(name = "inspector")
    var inspector: String,
    @ColumnInfo(name = "port")
    var port: String,
    @ColumnInfo(name = "inspectorName")
    var inspectorName: String ,
    @ColumnInfo(name = "inspectorType")
    var inspectorType: String ,
    @ColumnInfo(name = "vessel")
    var vessel: String,
    @ColumnInfo(name = "category")
    var category: String,


)

