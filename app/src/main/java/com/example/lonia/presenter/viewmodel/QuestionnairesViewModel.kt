package com.example.lonia.presenter.viewmodel

import android.content.Context
import android.util.Log
import androidx.annotation.StringRes
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.lonia.R
import com.example.lonia.domain.exceptions.InternetConnectionException
import com.example.lonia.domain.exceptions.NetworkException
import com.example.lonia.domain.model.BriefCase
import com.example.lonia.domain.model.Questionnaires
import com.example.lonia.domain.model.Questions
import com.example.lonia.domain.usecase.AddBriefCaseUseCase
import com.example.lonia.domain.usecase.FetchQuestionsUseCase
import com.example.lonia.domain.usecase.GetQuestionnairesUseCase
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

        getQuestionnairesUseCase.execute().fold({
            screenStateData.emit(State.Success(it))
        }, {
            when (it) {
                is NetworkException -> {
                    screenStateData.emit(State.Error(it.errorMessage))
                }
                is InternetConnectionException -> {
                    screenStateData.emit(State.Error(it.errorMessage))
                }
                else -> {}
            }
        })
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
            var listResponse = emptyList<Questions>()

            val questionsResponse = fetchQuestionsUseCase.execute(qid).fold({
                listResponse = it
            }, {
                when (it) {
                    is NetworkException -> {
                        screenStateData.emit(State.Error(it.errorMessage))
                    } //
                    is InternetConnectionException -> {
                        screenStateData.emit(State.Error(it.errorMessage))
                    }
                    else -> {}
                }
            }
            )

            if (!listResponse.isNullOrEmpty()) {
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
            } else {
                screenStateData.value = State.Error(R.string.server_error)
            }
        }
    }

    override fun onCleared() {
        viewModelScope.cancel()
    }

    sealed class State() {
        object DownWork : State()
        data class Error(@StringRes val exception: Int) : State()
        data class Success(val questionarries: List<Questionnaires>) : State()
        object Loading : State()
    }
}