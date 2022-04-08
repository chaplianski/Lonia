package com.example.testtask.domain.repository

import com.example.testtask.domain.model.BriefCase
import com.example.testtask.domain.model.Questions

interface BriefCaseRepository {

    fun addBriefCase(briefCase: BriefCase, questionsList: List<Questions>)

    fun getAllBriefCase(): List<BriefCase>
}