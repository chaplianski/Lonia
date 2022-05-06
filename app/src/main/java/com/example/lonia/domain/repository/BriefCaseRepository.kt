package com.example.lonia.domain.repository

import com.example.lonia.domain.model.BriefCase
import com.example.lonia.domain.model.Questions

interface BriefCaseRepository {

    fun addBriefCase(briefCase: BriefCase, questionsList: List<Questions>)

    fun getAllBriefCase(): List<BriefCase>
}