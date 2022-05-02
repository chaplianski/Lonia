package com.example.testtask.data.storage.database

import com.example.testtask.data.storage.dao.BriefcaseDao
import com.example.testtask.data.storage.model.PhotosData
import com.example.testtask.data.storage.storagies.PhotosStorage
import javax.inject.Inject

class PhotosStorageImpl @Inject constructor(): PhotosStorage {

    @Inject
    lateinit var briefcaseDao: BriefcaseDao

    override fun getPhotos(answerId: Long): List<PhotosData> {
       return briefcaseDao.getPhotos(answerId)
    }

    override fun updatePhotos(photosData: PhotosData) {
        briefcaseDao.updatePhotos(photosData)
    }

    override fun insertPhoto(photosData: PhotosData) {
        briefcaseDao.insertPhoto(photosData)
    }

    override fun deletePhoto(photosData: PhotosData) {
        briefcaseDao.deletePhotos(photosData)
    }
}