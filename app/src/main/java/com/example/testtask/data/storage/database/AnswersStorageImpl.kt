package com.example.testtask.data.storage.database

import com.example.testtask.data.storage.model.AnswersData
import com.example.testtask.data.storage.storagies.AnswersStorage
import javax.inject.Inject

class AnswersStorageImpl @Inject constructor(): AnswersStorage {

    override fun getAllAnswers(): List<AnswersData> {
        TODO("Not yet implemented")
    }
}