package com.example.testtask.data.storage.database

import android.content.Context
import com.example.testtask.data.storage.model.AnswersData
import com.example.testtask.data.storage.storagies.AnswersStorage
import javax.inject.Inject

class AnswersStorageImpl @Inject constructor(context: Context): AnswersStorage {

    val briefCaseDatabase = BriefCaseDB.getDatabase(context)

    override fun getAllAnswers(briefcaseId: Long): List<AnswersData> {
        return briefCaseDatabase.BriefCaseDao().getAnsweredAnswer(briefcaseId)
    }
}