package com.example.lonia.data.storage.storagies

import com.example.lonia.data.storage.model.PhotosData

interface PhotosStorage {

    fun getPhotos(questionId: String): List<PhotosData>

    fun updatePhotos(photosData: PhotosData)

    fun insertPhoto(photosData: PhotosData)

    fun deletePhoto(photosData: PhotosData)
}