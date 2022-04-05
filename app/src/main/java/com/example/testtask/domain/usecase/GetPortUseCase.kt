package com.example.testtask.domain.usecase

import com.example.testtask.domain.repository.PortRepository
import javax.inject.Inject

class GetPortUseCase @Inject constructor(private val portRepository: PortRepository) {

    fun execute (): List<String> {
        return portRepository.getPort()
    }
}