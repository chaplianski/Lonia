package com.example.lonia.domain.usecase

import com.example.lonia.domain.repository.PortRepository
import javax.inject.Inject

class GetPortUseCase @Inject constructor(private val portRepository: PortRepository) {

    fun execute (): List<String> {
        return portRepository.getPort()
    }
}