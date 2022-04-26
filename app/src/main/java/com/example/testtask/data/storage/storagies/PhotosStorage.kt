package com.example.testtask.data.storage.storagies

import com.example.testtask.data.storage.model.PhotosData

interface PhotosStorage {

    fun getPhotos(answerId: Long): List<PhotosData>

    fun updatePhotos(photosData: PhotosData)

    fun insertPhoto(photosData: PhotosData)
}