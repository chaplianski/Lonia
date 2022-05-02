package com.example.testtask.domain.usecase

import com.example.testtask.domain.model.Photos
import com.example.testtask.domain.repository.PhotosRepository
import javax.inject.Inject

class DeletePhotoUseCase @Inject constructor(private val photosRepository: PhotosRepository){

    fun execute (photo: Photos) {
        photosRepository.deletePhotos(photo)
    }
}