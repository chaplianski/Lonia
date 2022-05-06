package com.example.lonia.domain.usecase

import com.example.lonia.domain.repository.PhotosRepository
import com.example.lonia.domain.model.Photos
import javax.inject.Inject

class UpdatePhotosUseCase @Inject constructor(private val photosRepository: PhotosRepository) {

    fun execute(photos: Photos){
        photosRepository.updatePhotos(photos)
    }
}