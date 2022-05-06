package com.example.lonia.domain.model

import android.graphics.Bitmap

data class Photos(
    val photoId: Long,
    val questionId: String,
    val photoUri: Bitmap
)


