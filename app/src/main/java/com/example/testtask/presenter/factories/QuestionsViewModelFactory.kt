package com.example.testtask.presenter.factories

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.testtask.domain.usecase.GetQuestionsUseCase
import com.example.testtask.presenter.viewmodel.QuestionsViewModel
import javax.inject.Inject

@Suppress ("UNCHECKED_CAST")
class QuestionsViewModelFactory @Inject constructor(private val getQuestionsUseCase: GetQuestionsUseCase): ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return QuestionsViewModel (getQuestionsUseCase) as T
    }
}