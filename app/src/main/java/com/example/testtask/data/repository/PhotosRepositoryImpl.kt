package com.example.testtask.data.repository

import com.example.testtask.data.storage.database.PhotosStorageImpl
import com.example.testtask.domain.model.Photos
import com.example.testtask.domain.repository.PhotosRepository
import javax.inject.Inject

class PhotosRepositoryImpl @Inject constructor(private val photosStorage: PhotosStorageImpl): PhotosRepository {

    override fun getPhotos(questionId: String): List<Photos> {
        return photosStorage.getPhotos(questionId).map { it.photosMapDataToDomain() }
    }

    override fun updatePhotos(photos: Photos) {
        val photosData = photos.photosMapDomainToData()
        photosStorage.updatePhotos(photosData)
    }

    override fun insertPhotos(photos: Photos) {
        val photosData = photos.photosMapDomainToData()
        photosStorage.insertPhoto(photosData)
    }

    override fun deletePhotos(photos: Photos) {
        photosStorage.deletePhoto(photos.photosMapDomainToData())
    }
}