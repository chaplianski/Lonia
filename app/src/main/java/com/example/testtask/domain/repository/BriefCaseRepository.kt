package com.example.testtask.domain.repository

import com.example.testtask.domain.model.BriefCase

interface BriefCaseRepository {

    fun getBriefCase(id: Int) : BriefCase

    fun addBriefCase(briefCase: BriefCase)

    fun getAllBriefCase(): List<BriefCase>


}