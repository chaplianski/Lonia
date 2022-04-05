package com.example.testtask.data.storage.database

import android.content.Context
import com.example.testtask.data.storage.model.QuestionsData
import com.example.testtask.data.storage.storagies.QuestionsStorage
import javax.inject.Inject

class QuestionsStorageImpl @Inject constructor(context: Context) : QuestionsStorage {

    val briefCaseDatabase = BriefCaseDB.getDatabase(context)

    override fun getQuestionsList(): List<QuestionsData> {
        TODO("Not yet implemented")
    }

    override fun addAllQuestions(questionsData: QuestionsData) {
//        briefCaseDatabase.BriefcaseWithQuestions().questionsData
    }
}