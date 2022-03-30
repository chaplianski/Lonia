package com.example.testtask.domain.usecase

import com.example.testtask.domain.repository.PortRepository

class GetPortUseCase (private val portRepository: PortRepository) {

    fun execute (){
        portRepository.getPort()
    }
}