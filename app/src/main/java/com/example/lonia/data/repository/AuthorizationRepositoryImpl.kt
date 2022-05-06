package com.example.lonia.data.repository

import com.example.lonia.data.storage.network.retrofit.LoginRequestApiHelper
import com.example.lonia.domain.model.LoginRequest
import com.example.lonia.domain.repository.AuthorizationRepository
import javax.inject.Inject

class AuthorizationRepositoryImpl @Inject constructor(val loginRequestApiHelper: LoginRequestApiHelper):
    AuthorizationRepository {

    override suspend fun getToken(loginRequest: LoginRequest): Int {
        return loginRequestApiHelper.fetchToken(loginRequest.loginMapDomainToData())

    }
}