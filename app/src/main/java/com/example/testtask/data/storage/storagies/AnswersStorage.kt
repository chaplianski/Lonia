package com.example.testtask.data.storage.storagies

import com.example.testtask.data.storage.model.AnswersData

interface AnswersStorage {

    fun getAllAnswers(): List<AnswersData>
}