package com.example.lonia.presenter.factories

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.lonia.domain.usecase.AddBriefCaseUseCase
import com.example.lonia.domain.usecase.FetchQuestionsUseCase
import com.example.lonia.domain.usecase.GetQuestionnairesUseCase
import com.example.lonia.presenter.viewmodel.QuestionnairesViewModel
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