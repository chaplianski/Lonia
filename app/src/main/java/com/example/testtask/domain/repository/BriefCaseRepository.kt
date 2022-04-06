package com.example.testtask.domain.repository

import com.example.testtask.data.storage.model.BriefcaseWithQuestions
import com.example.testtask.domain.model.BriefCase
import com.example.testtask.domain.model.Questions

interface BriefCaseRepository {

    fun getBriefCase(id: Long) : BriefCase

    fun addBriefCase(briefCase: BriefCase, questionsList: List<Questions>)

    fun getAllBriefCase(): List<BriefCase>


}