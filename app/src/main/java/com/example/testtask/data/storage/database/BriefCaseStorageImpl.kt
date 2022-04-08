package com.example.testtask.data.storage.database

import com.example.testtask.data.storage.dao.BriefcaseDao
import com.example.testtask.data.storage.storagies.BriefCaseStorage
import com.example.testtask.data.storage.model.BriefCaseData
import com.example.testtask.data.storage.model.QuestionsData
import javax.inject.Inject

class BriefCaseStorageImpl @Inject constructor() : BriefCaseStorage {

    @Inject
    lateinit var briefcaseDao: BriefcaseDao

    override fun addBriefCaseData(
        briefCaseData: BriefCaseData,
        listQuestionsData: List<QuestionsData>
    ) {
        briefcaseDao.insert(briefCaseData, listQuestionsData)
    }

    override fun getBriefCase(briefCaseId: Long): BriefCaseData {
        return briefcaseDao.getBriefCase(briefCaseId)
    }

    override fun getAllBriefCase(): List<BriefCaseData> {
        return briefcaseDao.getAllBriefCases()
    }
}