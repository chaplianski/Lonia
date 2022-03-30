package com.example.testtask.domain.usecase

import com.example.testtask.data.repository.VesselsRepositoryImpl
import com.example.testtask.domain.repository.VesselsRepository
import javax.inject.Inject

class GetVesselsUseCase @Inject constructor (val vesselsRepositoryImpl: VesselsRepositoryImpl) {

    fun execute(): List<String>{
       return vesselsRepositoryImpl.getVessels()
    }
}