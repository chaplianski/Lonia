package com.example.testtask.presenter.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.testtask.domain.model.BriefCase
import com.example.testtask.domain.model.BriefcasesAndQuestions
import com.example.testtask.domain.model.Questions
import com.example.testtask.domain.usecase.GetAllBriefCasesUseCase
import com.example.testtask.domain.usecase.GetQuestionsUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

class BriefCaseViewModel @Inject constructor(
    private val getAllBriefCasesUseCase: GetAllBriefCasesUseCase,
    private val getQuestionsUseCase: GetQuestionsUseCase
    ): ViewModel() {

//    val _briefCase = MutableLiveData<List<BriefCase>>()
//    val briefCase: LiveData<List<BriefCase>> get() = _briefCase

    val _briefCase = MutableLiveData<List<BriefcasesAndQuestions>>()
    val briefCase: LiveData<List<BriefcasesAndQuestions>> get() = _briefCase

    fun getBriefCaseList(){
       viewModelScope.launch(Dispatchers.IO) {
           val briefcaseAndAnaliticList : MutableList<BriefcasesAndQuestions> = mutableListOf()
           val briefCaseList = getAllBriefCasesUseCase.execute()
           for (brief in briefCaseList){
              val questionsList = getQuestions(brief.briefCaseId)
              val questionsCount = questionsList.size
              var answeredCount = 0
               for (question in questionsList){
                  if (question.isAnswered){
                      answeredCount++
                  }
              }
           val briefcaseAndAnalitic = BriefcasesAndQuestions(
               briefCaseId = brief.briefCaseId,
               dateOfCreation = brief.dateOfCreation,
               inspector = brief.inspector,
               port = brief.port,
               inspectorName = brief.inspectorName,
               inspectorType = brief.inspectorType,
               vessel = brief.vessel,
               category = brief.category,
               answered = answeredCount,
               total = questionsCount
           )
           briefcaseAndAnaliticList.add(briefcaseAndAnalitic)
           }

           _briefCase.postValue(briefcaseAndAnaliticList)





  //         _briefCase.postValue(briefCaseList)
       }
    }

    fun getQuestions (briefcaseId: Long): List<Questions>{
        return getQuestionsUseCase.execute(briefcaseId)
    }

}