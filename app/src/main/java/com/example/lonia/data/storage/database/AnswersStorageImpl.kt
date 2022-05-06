package com.example.lonia.data.storage.database

import com.example.lonia.data.storage.dao.BriefcaseDao
import com.example.lonia.data.storage.model.AnswersData
import com.example.lonia.data.storage.model.QuestionsData
import com.example.lonia.data.storage.storagies.AnswersStorage
import javax.inject.Inject

class AnswersStorageImpl @Inject constructor() : AnswersStorage {

    @Inject
    lateinit var briefcaseDao: BriefcaseDao

    override fun getAnsweredQuestions(briefcaseId: Long): List<QuestionsData> {
        return briefcaseDao.getAnsweredQuestions(briefcaseId)
    }

    override fun updatedAnswer(answersData: AnswersData) {
        briefcaseDao.updateAnswer(answersData)
    }

    override fun getAnswer(answerId: Long): AnswersData {
        return briefcaseDao.getAnswer(answerId)
    }
}