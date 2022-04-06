package com.example.testtask.data.storage.database

import android.content.Context
import com.example.testtask.data.storage.model.AnswersData
import com.example.testtask.data.storage.model.QuestionsData
import com.example.testtask.data.storage.storagies.QuestionsStorage
import javax.inject.Inject

class QuestionsStorageImpl @Inject constructor(context: Context) : QuestionsStorage {

    val briefCaseDatabase = BriefCaseDB.getDatabase(context)

   override fun getQuestionsList(briefcaseId: Long): List<QuestionsData> {
        return briefCaseDatabase.BriefCaseDao().getAllQuestions(briefcaseId)
    }

    override fun updateQuestions(questionsData: QuestionsData, answersData: AnswersData) {
        briefCaseDatabase.BriefCaseDao().updateQuestionsAndInsertAnswer(questionsData, answersData)
    }

 //   override fun Questions(questionsData: QuestionsData) {
 //       TODO()
        //briefCaseDatabase.BriefcaseWithQuestions().questionsData
 //   }
}