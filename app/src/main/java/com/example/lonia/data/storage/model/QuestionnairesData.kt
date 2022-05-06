package com.example.lonia.data.storage.model

import com.squareup.moshi.Json


data class QuestionnairesData(

        @Json(name="qid")
        val qid: Int,
        @Json(name="title")
        var title: String
)

