package com.example.lonia.data.storage.database

import com.example.lonia.data.storage.dao.BriefcaseDao
import com.example.lonia.data.storage.model.PhotosData
import com.example.lonia.data.storage.storagies.PhotosStorage
import javax.inject.Inject

class PhotosStorageImpl @Inject constructor(): PhotosStorage {

    @Inject
    lateinit var briefcaseDao: BriefcaseDao

    override fun getPhotos(questionId: String): List<PhotosData> {
       return briefcaseDao.getPhotos(questionId)
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