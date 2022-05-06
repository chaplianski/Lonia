package com.example.testtask.domain.usecase

import com.example.testtask.domain.model.Photos
import com.example.testtask.domain.repository.PhotosRepository
import javax.inject.Inject

class GetPhotosUseCase @Inject constructor(private val photosRepository: PhotosRepository){

    fun execute(questionId: String): List<Photos>{
        return photosRepository.getPhotos(questionId)
    }
}