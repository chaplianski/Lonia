package com.example.lonia.domain.usecase

import com.example.lonia.R
import com.example.lonia.domain.exceptions.InternetConnectionException
import com.example.lonia.domain.exceptions.NetworkException
import com.example.lonia.domain.model.Questionnaires
import com.example.lonia.domain.repository.QuestionnairesRepository
import java.io.IOException
import java.net.ConnectException
import java.net.UnknownHostException
import javax.inject.Inject

class GetQuestionnairesUseCase @Inject constructor(private val questionnairesRepository: QuestionnairesRepository) {



    suspend fun execute (): Result<List<Questionnaires>> {
        return Result.runCatching {
            try {
                questionnairesRepository.getQuestionnaires()
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