package com.example.testtask.data.storage.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity (tableName = "photos")
data class PhotosData(
    @PrimaryKey(autoGenerate = true)
    val photoId: Long,
    val answerId: Long,
    val photoUri: String
)
