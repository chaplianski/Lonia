package com.example.lonia.data.storage.database

import com.example.lonia.data.storage.dao.BriefcaseDao
import com.example.lonia.data.storage.model.BriefCaseData
import com.example.lonia.data.storage.model.QuestionsData
import com.example.lonia.data.storage.storagies.BriefCaseStorage
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

    override fun deleteBriefcase(briefCaseId: Long) {
        briefcaseDao.deleleBriefcaseQuestionsPhotos(briefCaseId)
    }
}