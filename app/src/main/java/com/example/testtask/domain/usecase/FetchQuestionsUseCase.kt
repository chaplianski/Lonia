package com.example.testtask.domain.usecase

import com.example.testtask.domain.exceptions.InternetConnectionException
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
            } catch (e: IOException) {
                throw  InternetConnectionException()
            } catch (e: UnknownHostException) {
                throw  InternetConnectionException()
            } catch (e: ConnectException) {
                throw  InternetConnectionException()
            }
        }
    }
}