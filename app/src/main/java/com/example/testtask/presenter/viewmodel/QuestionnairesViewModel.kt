package com.example.testtask.presenter.viewmodel

import android.content.Context
import android.util.Log
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
import com.example.testtask.presenter.ui.QuestionnairesFragment
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.cancel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
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

    private val screenStateData = MutableStateFlow<State>(
        State.Work
    )
    val screenState = screenStateData.asStateFlow()


    val _qestions = MutableLiveData<List<Questions>>()
    val questions: LiveData<List<Questions>> get() = _qestions

    suspend fun getQuestionnairesList() {
        val list = getQuestionnairesUseCase.execute()
        _qestionnaires.postValue(list)
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

            screenStateData.value = State.Work

            val list = fetchQuestionsUseCase.execute(qid)

            val briefCase = BriefCase(
                vessel = vessel,
                inspectorType = inspectorType,
                inspectorName = inspectorName,
                inspector = inspector,
                category = category,
                dateOfCreation = Date().time,
                port = port,
                briefCaseId = 0L,
            )
            addBriefCaseUseCase.execute(briefCase, list)

            screenStateData.value = State.DownWork
        }
    }

    override fun onCleared() {
        viewModelScope.cancel()
    }

    sealed class State() {
        object Work : State()
        object DownWork : State()
    }

}