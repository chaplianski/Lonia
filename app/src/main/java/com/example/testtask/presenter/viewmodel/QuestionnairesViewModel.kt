package com.example.testtask.presenter.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.testtask.domain.model.BriefCase
import com.example.testtask.domain.model.Questionnaires
import com.example.testtask.domain.usecase.AddBriefCaseUseCase
import com.example.testtask.domain.usecase.FetchQuestionsUseCase
import com.example.testtask.domain.usecase.GetQuestionnairesUseCase
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

    private val screenStateData = MutableStateFlow<State>(
        State.Loading
    )
    val screenState = screenStateData.asStateFlow()

    suspend fun getQuestionnairesList() {

        val questionnairesResponse = getQuestionnairesUseCase.execute()
        val questionnairesList = questionnairesResponse.response

        if (!questionnairesList.isNullOrEmpty()){
           val list = questionnairesResponse.response
           screenStateData.emit(State.Success(questionnairesList))
        }else{
           screenStateData.value = State.Error(questionnairesResponse.status)

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

            screenStateData.value = State.Loading

            val questionsResponse = fetchQuestionsUseCase.execute(qid)
            val listResponse = questionsResponse.response
            if (!listResponse.isNullOrEmpty()){
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

                addBriefCaseUseCase.execute(briefCase, listResponse)

                screenStateData.value = State.DownWork
            }else{
                screenStateData.value = State.Error(questionsResponse.status)
            }


        }
    }

    override fun onCleared() {
        viewModelScope.cancel()
    }

    sealed class State() {
        object DownWork : State()
        data class Error(val exception: String) : State()
        data class Success (val questionarries: List<Questionnaires>): State()
        object Loading : State()
    }



}