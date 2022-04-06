package com.example.testtask.data.repository

import com.example.testtask.data.storage.storagies.BriefCaseStorage
import com.example.testtask.data.storage.model.BriefCaseData
import com.example.testtask.data.storage.model.BriefcaseWithQuestions
import com.example.testtask.data.storage.model.QuestionsData
import com.example.testtask.domain.model.BriefCase
import com.example.testtask.domain.model.Questions
import com.example.testtask.domain.repository.BriefCaseRepository
import javax.inject.Inject

class BriefCaseRepositoryImpl @Inject constructor(private val briefCaseStorage: BriefCaseStorage) :
    BriefCaseRepository {

 //   override fun addBriefCase(briefCase: BriefCase) {
 //       val briefCaseData = Mapper.briefcaseMapDomainToData(briefCase)
  //      briefCaseStorage.addBriefCaseData(briefCaseData)
    //      }

    override fun getAllBriefCase(): List<BriefCase> {
           return briefCaseStorage.getAllBriefCase().map {it.briefcaseMapDataToDomain()} //allBriefCases.toList()
    }

    override fun getBriefCase(briefCaseId: Long): BriefCase {
      //  val briefCaseData = briefCaseStorage.getBriefCase(briefCaseId)
     //   return Mapper.briefcaseMapDataToDomain(briefCaseData)
     //  return briefCaseStorage.getBriefCase(briefCaseId).map {it.briefcaseMapDataToDomain()}

        TODO()
    }

    override fun addBriefCase(briefcase: BriefCase, listQuestions: List<Questions>) {
        val briefCaseData = Mapper.briefcaseMapDomainToData(briefcase)
        val listQuestionsData = mutableListOf<QuestionsData>()
        for (i in listQuestions) {
            listQuestionsData.add(Mapper.questionsMapDomainToData(i))
        }
        briefCaseStorage.addBriefCaseData(briefCaseData, listQuestionsData)
    }
}