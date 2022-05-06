package com.example.lonia.domain.usecase

import com.example.lonia.R
import com.example.lonia.domain.exceptions.InternetConnectionException
import com.example.lonia.domain.exceptions.NetworkException
import com.example.lonia.domain.model.LoginRequest
import com.example.lonia.domain.repository.AuthorizationRepository
import java.io.IOException
import java.net.ConnectException
import java.net.UnknownHostException
import javax.inject.Inject

class getTokenUseCase @Inject constructor(val authorizationRepository: AuthorizationRepository){


    suspend fun execute(loginRequest: LoginRequest): Result<Int> {
        return Result.runCatching {
            try {
                authorizationRepository.getToken(loginRequest)
            }catch (e: IOException){
                throw  InternetConnectionException(R.string.internet_error)
            }catch (e: UnknownHostException){
                throw  NetworkException(R.string.server_error)
            }catch (e: ConnectException){
                throw  InternetConnectionException(R.string.client_error)
            }
        }
    }
}