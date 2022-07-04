package com.example.lonia.domain.usecase

import com.example.lonia.R
import com.example.lonia.domain.exceptions.InternetConnectionException
import com.example.lonia.domain.exceptions.NetworkException
import com.example.lonia.domain.repository.VesselsRepository
import java.io.IOException
import java.net.ConnectException
import java.net.UnknownHostException
import javax.inject.Inject

class GetVesselsUseCase @Inject constructor (private val vesselsRepository: VesselsRepository) {

    suspend fun execute(): Result<List<String>>{
        return Result.runCatching {
            try {
                vesselsRepository.getVessels()
            }catch (e: IOException){
                throw  InternetConnectionException(R.string.internet_error)
            }catch (e: UnknownHostException){
                throw  NetworkException(R.string.server_error)
            }catch (e: ConnectException){
                throw  InternetConnectionException(R.string.client_error)
            }
        }
   //    return vesselsRepository.getVessels()
    }
}