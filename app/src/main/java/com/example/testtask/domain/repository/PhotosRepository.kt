package com.example.testtask.domain.repository

import com.example.testtask.domain.model.Photos

interface PhotosRepository {

    fun getPhotos (answerId: Long): List<Photos>

    fun updatePhotos(photos: Photos)

    fun insertPhotos(photos: Photos)

    fun deletePhotos(photos: Photos)
}