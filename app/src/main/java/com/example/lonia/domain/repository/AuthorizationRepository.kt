package com.example.lonia.domain.repository

import com.example.lonia.domain.model.LoginRequest

interface AuthorizationRepository {

    suspend fun getToken (loginRequest: LoginRequest): Int
}