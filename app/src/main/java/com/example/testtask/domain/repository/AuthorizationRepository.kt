package com.example.testtask.domain.repository

import com.example.testtask.domain.model.LoginRequest
import com.example.testtask.domain.model.LoginResponse

interface AuthorizationRepository {

    suspend fun getToken (loginRequest: LoginRequest): Int
}