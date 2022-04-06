package com.example.testtask.data.storage.model

import androidx.room.ColumnInfo
import androidx.room.Embedded
import androidx.room.Relation

data class BriefcaseWithQuestions(

    @Embedded val briefCaseData: BriefCaseData,

    @Relation(
        parentColumn = "briefCaseId",
        entityColumn = "idQuestion",

    )
    val questionsDataList: List<QuestionsData>




)
