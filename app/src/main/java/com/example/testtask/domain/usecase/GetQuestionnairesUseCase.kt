package com.example.testtask.domain.usecase

import com.example.testtask.domain.exceptions.InternetConnectionException
import com.example.testtask.domain.model.Questionnaires
import com.example.testtask.domain.repository.QuestionnairesRepository
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
                throw  InternetConnectionException()
            }catch (e: UnknownHostException){
                throw  InternetConnectionException()
            }catch (e: ConnectException){
                throw  InternetConnectionException()
            }
        }
    }
}