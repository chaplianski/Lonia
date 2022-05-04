package com.example.testtask.domain.usecase

import android.annotation.SuppressLint
import com.example.testtask.R
import com.example.testtask.domain.exceptions.InternetConnectionException
import com.example.testtask.domain.exceptions.NetworkException
import com.example.testtask.domain.model.LoginRequest
import com.example.testtask.domain.model.LoginResponse
import com.example.testtask.domain.repository.AuthorizationRepository
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