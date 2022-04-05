package com.example.testtask.presenter.viewmodel

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.Navigation
import com.example.testtask.R
import com.example.testtask.domain.model.BriefCase
import com.example.testtask.domain.model.Questionnaires
import com.example.testtask.domain.model.Questions
import com.example.testtask.domain.usecase.AddBriefCaseUseCase
import com.example.testtask.domain.usecase.FetchQuestionsUseCase
import com.example.testtask.domain.usecase.GetQuestionnairesUseCase
import com.example.testtask.presenter.ui.Constants
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.util.*
import javax.inject.Inject

class QuestionnairesViewModel @Inject constructor(
    private val getQuestionnairesUseCase: GetQuestionnairesUseCase,
    private val addBriefCaseUseCase: AddBriefCaseUseCase,
    private val fetchQuestionsUseCase: FetchQuestionsUseCase
) : ViewModel() {

    val _qestionnaires = MutableLiveData<List<Questionnaires>>()
    val questionnaires: LiveData<List<Questionnaires>> get() = _qestionnaires

    val _qestions = MutableLiveData<List<Questions>>()
    val questions: LiveData<List<Questions>> get() = _qestions

    suspend fun getQuestionnairesList() {
        val list = getQuestionnairesUseCase.execute()
        _qestionnaires.postValue(list)
    }

    fun addBriefcase(briefcase: BriefCase) {
        viewModelScope.launch(Dispatchers.IO) {
            addBriefCaseUseCase.execute(briefcase)
        }
    }

    fun saveBriefcase(
        vessel: String,
        inspectorType: String,
        inspectorName: String,
        inspector: String,
        category: String,
        port: String,
        qid: Int
    ) {
        viewModelScope.launch(Dispatchers.IO) {
            val list = fetchQuestionsUseCase.execute(qid)
            val briefCase = BriefCase(
                vessel = vessel,
                inspectorType = inspectorType,
                inspectorName = inspectorName,
                inspector = inspector,
                category = category,
                dateOfCreation = Date().time,
                port = port,
                briefCaseId = 0,

            )
            addBriefCaseUseCase.execute(briefCase)

        }


    }


}