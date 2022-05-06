package com.example.testtask.domain.repository

import com.example.testtask.domain.model.Photos

interface PhotosRepository {

    fun getPhotos (questionId: String): List<Photos>

    fun updatePhotos(photos: Photos)

    fun insertPhotos(photos: Photos)

    fun deletePhotos(photos: Photos)
}