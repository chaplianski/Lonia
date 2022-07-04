package com.example.lonia.domain.usecase

import com.example.lonia.R
import com.example.lonia.domain.exceptions.InternetConnectionException
import com.example.lonia.domain.exceptions.NetworkException
import com.example.lonia.domain.repository.InspectionTypeRepository
import java.io.IOException
import java.net.ConnectException
import java.net.UnknownHostException
import javax.inject.Inject

class GetInspectionTypeUseCase @Inject constructor (private val inspectionTypeRepository: InspectionTypeRepository) {

    suspend fun execute(): Result<List<String>>{
        return Result.runCatching {
            try {
                inspectionTypeRepository.getInspectionType()
            }catch (e: IOException){
                throw  InternetConnectionException(R.string.internet_error)
            }catch (e: UnknownHostException){
                throw  NetworkException(R.string.server_error)
            }catch (e: ConnectException){
                throw  InternetConnectionException(R.string.client_error)
            }
        }
//        return inspectionTypeRepository.getInspectionType()
    }
}