package com.example.testtask.data.storage.database

import android.content.Context
import com.example.testtask.data.storage.storagies.BriefCaseStorage
import com.example.testtask.data.storage.model.BriefCaseData
import com.example.testtask.data.storage.model.BriefcaseWithQuestions
import com.example.testtask.data.storage.model.QuestionsData
import javax.inject.Inject

class BriefCaseStorageImpl @Inject constructor (context: Context) : BriefCaseStorage {


    val briefCaseDatabase = BriefCaseDB.getDatabase(context)

    override fun addBriefCaseData(
        briefCaseData: BriefCaseData,
        listQuestionsData: List<QuestionsData>
    ) {
        briefCaseDatabase.BriefCaseDao().insert(briefCaseData, listQuestionsData)
    }

    override fun getBriefCase(briefCaseId: Long): BriefCaseData {
        return briefCaseDatabase.BriefCaseDao().getBriefCase(briefCaseId)
    }

    override fun getAllBriefCase(): List<BriefCaseData> {
        return briefCaseDatabase.BriefCaseDao().getAllBriefCases()
    }
}