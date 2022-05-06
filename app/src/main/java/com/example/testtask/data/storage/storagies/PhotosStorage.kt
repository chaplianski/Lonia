package com.example.testtask.data.storage.storagies

import com.example.testtask.data.storage.model.PhotosData

interface PhotosStorage {

    fun getPhotos(questionId: String): List<PhotosData>

    fun updatePhotos(photosData: PhotosData)

    fun insertPhoto(photosData: PhotosData)

    fun deletePhoto(photosData: PhotosData)
}