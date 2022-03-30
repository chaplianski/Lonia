package com.example.testtask.data.storage.database

import android.content.Context
import com.example.testtask.data.storage.BriefCaseStorage
import com.example.testtask.data.storage.model.BriefCaseData

class BriefCaseStorageImpl (context: Context) : BriefCaseStorage {

    val briefCaseDatabase = BriefCaseDB.getDatabase(context)

    override fun addBriefCaseData(briefCaseData: BriefCaseData) {
        briefCaseDatabase.BriefCaseDao().insertBriefCase(briefCaseData)
    }

    override fun getBriefCase(briefCaseId: Int): BriefCaseData {
        return briefCaseDatabase.BriefCaseDao().getBriefCase(briefCaseId)
    }

    override fun getAllBriefCase(): List<BriefCaseData> {
        return briefCaseDatabase.BriefCaseDao().getAllBriefCases()
    }
}