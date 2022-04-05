package com.example.testtask.data.storage.database

import android.content.Context
import com.example.testtask.data.storage.storagies.BriefCaseStorage
import com.example.testtask.data.storage.model.BriefCaseData
import javax.inject.Inject

class BriefCaseStorageImpl @Inject constructor (context: Context) : BriefCaseStorage {

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