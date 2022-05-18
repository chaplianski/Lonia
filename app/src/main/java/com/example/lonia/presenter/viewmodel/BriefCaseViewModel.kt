package com.example.lonia.presenter.viewmodel

import androidx.annotation.StringRes
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.lonia.domain.exceptions.InternetConnectionException
import com.example.lonia.domain.exceptions.NetworkException
import com.example.lonia.domain.model.BriefcasesAndQuestions
import com.example.lonia.domain.model.Questionnaires
import com.example.lonia.domain.model.Questions
import com.example.lonia.domain.usecase.DeleteBriefcaseUseCase
import com.example.lonia.domain.usecase.GetAllBriefCasesUseCase
import com.example.lonia.domain.usecase.GetQuestionsUseCase
import com.example.lonia.domain.usecase.SaveBriefcaseUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.cancel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

class BriefCaseViewModel @Inject constructor(
    private val getAllBriefCasesUseCase: GetAllBriefCasesUseCase,
    private val getQuestionsUseCase: GetQuestionsUseCase,
    private val saveBriefcaseUseCase: SaveBriefcaseUseCase,
    private val deleteBriefcaseUseCase: DeleteBriefcaseUseCase
) : ViewModel() {

    val _briefCase = MutableLiveData<List<BriefcasesAndQuestions>>()
    val briefCase: LiveData<List<BriefcasesAndQuestions>> get() = _briefCase

    private val screenStateData = MutableStateFlow<BriefcaseState>(
        BriefcaseState.Loading
    )
    val screenState = screenStateData.asStateFlow()
    var saveresult = ""


    fun getBriefCaseList() {
        viewModelScope.launch(Dispatchers.IO) {
            val briefcaseAndAnaliticList: MutableList<BriefcasesAndQuestions> = mutableListOf()
            val briefCaseList = getAllBriefCasesUseCase.execute()
            for (brief in briefCaseList) {
                val questionsList = getQuestions(brief.briefCaseId)
                val questionsCount = questionsList.size
                var answeredCount = 0
                for (question in questionsList) {
                    if (question.isAnswered) {
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
            screenStateData.emit(BriefcaseState.ShowData(briefcaseAndAnaliticList))
        }
    }

    fun getQuestions(briefcaseId: Long): List<Questions> {
        return getQuestionsUseCase.execute(briefcaseId)
    }

    fun saveBriefcase(briefcaseId: Long) {
        viewModelScope.launch(Dispatchers.IO) {
            screenStateData.value = BriefcaseState.Loading
            saveBriefcaseUseCase.execute(briefcaseId).fold({
                screenStateData.value = BriefcaseState.Success
                saveresult = it
            }, {
                when (it) {
                    is NetworkException -> {
                        screenStateData.emit(BriefcaseState.Error(it.errorMessage))
                    }
                    is InternetConnectionException -> {
                        screenStateData.emit(BriefcaseState.Error(it.errorMessage))
                    }
                    else -> {}
                }
            }

            )

        }
    }

    fun  deleteBriefcase(briefcaseId: Long){
        viewModelScope.launch(Dispatchers.IO) {
            deleteBriefcaseUseCase.execute(briefcaseId)
        }
    }

     fun saveAndDeleteBriefcase(briefcaseId: Long){
        saveBriefcase(briefcaseId)
        if (saveresult == "Success!")
        deleteBriefcase(briefcaseId)
        getBriefCaseList()
     }

    override fun onCleared() {
        viewModelScope.cancel()
    }


    sealed class BriefcaseState() {
        data class Error(@StringRes val exception: Int) : BriefcaseState()
        object Success : BriefcaseState()
        object Loading : BriefcaseState()
        data class ShowData(val briefcasesAndQuestionsList: List<BriefcasesAndQuestions>) :
            BriefcaseState()
    }
}