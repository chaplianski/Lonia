package com.example.lonia.domain.usecase

import com.example.lonia.domain.repository.PhotosRepository
import com.example.lonia.domain.model.Photos
import javax.inject.Inject

class GetPhotosUseCase @Inject constructor(private val photosRepository: PhotosRepository){

    fun execute(questionId: String): List<Photos>{
        return photosRepository.getPhotos(questionId)
    }
}