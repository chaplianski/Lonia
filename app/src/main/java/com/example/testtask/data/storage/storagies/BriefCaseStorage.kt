package com.example.testtask.data.storage.storagies

import com.example.testtask.data.storage.model.BriefCaseData

interface BriefCaseStorage {

    fun addBriefCaseData (briefCaseData: BriefCaseData)

    fun getBriefCase (briefCaseId: Int): BriefCaseData

    fun getAllBriefCase (): List<BriefCaseData>
}