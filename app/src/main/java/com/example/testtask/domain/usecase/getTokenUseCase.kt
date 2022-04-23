package com.example.testtask.domain.usecase

import com.example.testtask.domain.model.LoginRequest
import com.example.testtask.domain.model.LoginResponse
import com.example.testtask.domain.repository.AuthorizationRepository
import javax.inject.Inject

class getTokenUseCase @Inject constructor(val authorizationRepository: AuthorizationRepository){

    suspend fun execute(loginRequest: LoginRequest): LoginResponse{
        return authorizationRepository.getToken(loginRequest)
    }
}