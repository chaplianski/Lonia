package com.example.lonia.data.storage.model

import android.graphics.Bitmap
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity (tableName = "photos")
data class PhotosData(
    @PrimaryKey(autoGenerate = true)
    val photoId: Long,
    val questionId: String,
    val photoUri: Bitmap
)
