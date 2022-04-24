package com.example.testtask.data.repository

import com.example.testtask.data.storage.network.retrofit.LoginRequestApiHelper
import com.example.testtask.domain.model.LoginRequest
import com.example.testtask.domain.model.LoginResponse
import com.example.testtask.domain.repository.AuthorizationRepository
import javax.inject.Inject

class AuthorizationRepositoryImpl @Inject constructor(val loginRequestApiHelper: LoginRequestApiHelper): AuthorizationRepository {

    override suspend fun getToken(loginRequest: LoginRequest): LoginResponse {
        return LoginResponse(
            token = loginRequestApiHelper.fetchToken(loginRequest.loginMapDomainToData()).token
        )
    }
}