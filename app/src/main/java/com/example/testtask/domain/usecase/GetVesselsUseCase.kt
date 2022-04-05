package com.example.testtask.domain.usecase

import com.example.testtask.data.repository.VesselsRepositoryImpl
import com.example.testtask.domain.repository.VesselsRepository
import javax.inject.Inject

class GetVesselsUseCase @Inject constructor (private val vesselsRepository: VesselsRepository) {

    fun execute(): List<String>{
       return vesselsRepository.getVessels()
    }
}