package com.example.testtask.presenter.factories

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.testtask.domain.usecase.AddBriefCaseUseCase
import com.example.testtask.domain.usecase.FetchQuestionsUseCase
import com.example.testtask.domain.usecase.GetQuestionnairesUseCase
import com.example.testtask.presenter.viewmodel.QuestionnairesViewModel
import javax.inject.Inject

@Suppress("UNCHECKED_CAST")
class QuestionnairesViewModelFactory @Inject constructor(
    private val getQuestionnairesUseCase: GetQuestionnairesUseCase,
    private val addBriefCaseUseCase: AddBriefCaseUseCase,
    private val fetchQuestionsUseCase: FetchQuestionsUseCase
) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return QuestionnairesViewModel(
            getQuestionnairesUseCase,
            addBriefCaseUseCase,
            fetchQuestionsUseCase
        ) as T
    }
}