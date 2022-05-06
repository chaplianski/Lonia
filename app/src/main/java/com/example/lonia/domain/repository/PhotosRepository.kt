package com.example.lonia.domain.repository

import com.example.lonia.domain.model.Photos

interface PhotosRepository {

    fun getPhotos (questionId: String): List<Photos>

    fun updatePhotos(photos: Photos)

    fun insertPhotos(photos: Photos)

    fun deletePhotos(photos: Photos)
}