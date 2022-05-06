package com.example.lonia.domain.usecase

import com.example.lonia.domain.repository.VesselsRepository
import javax.inject.Inject

class GetVesselsUseCase @Inject constructor (private val vesselsRepository: VesselsRepository) {

    fun execute(): List<String>{
       return vesselsRepository.getVessels()
    }
}