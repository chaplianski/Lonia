package com.example.testtask.domain.usecase

import com.example.testtask.R
import com.example.testtask.domain.exceptions.InternetConnectionException
import com.example.testtask.domain.exceptions.NetworkException
import com.example.testtask.domain.model.Questionnaires
import com.example.testtask.domain.model.Questions
import com.example.testtask.domain.repository.QuestionsRepository
import java.io.IOException
import java.net.ConnectException
import java.net.UnknownHostException
import javax.inject.Inject

class GetQuestionsUseCase @Inject constructor (private val questionRepository: QuestionsRepository) {

    fun execute (briefcseId: Long): List<Questions>{
        return questionRepository.getQuestions(briefcseId)
    }


}