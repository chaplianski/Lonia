package com.example.testtask.domain.usecase

import com.example.testtask.R
import com.example.testtask.domain.exceptions.InternetConnectionException
import com.example.testtask.domain.exceptions.NetworkException
import com.example.testtask.domain.model.Questions
import com.example.testtask.domain.repository.QuestionsRepository
import java.io.IOException
import java.net.ConnectException
import java.net.UnknownHostException
import javax.inject.Inject

class FetchQuestionsUseCase @Inject constructor(private val questionsRepository: QuestionsRepository) {

    suspend fun execute(qid: Int): Result<List<Questions>> {

        return Result.runCatching {
            try {
                questionsRepository.fetchQuestions(qid)
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