package com.example.lonia.data.storage.storagies

import com.example.lonia.data.storage.model.BriefCaseData
import com.example.lonia.data.storage.model.QuestionsData

interface BriefCaseStorage {

    fun addBriefCaseData (briefCaseData: BriefCaseData, listQuestionsData: List<QuestionsData>)

    fun getBriefCase (briefCaseId: Long): BriefCaseData

    fun getAllBriefCase (): List<BriefCaseData>

}