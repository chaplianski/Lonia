package com.example.testtask.presenter.factories

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.testtask.domain.usecase.GetAnswersUseCase
import com.example.testtask.presenter.viewmodel.AnswersViewModel
import javax.inject.Inject

@Suppress("UNCHECKED_CAST")
class AnswersViewModelFactory @Inject constructor(private val getAnswersUseCase: GetAnswersUseCase) :
    ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return AnswersViewModel(getAnswersUseCase) as T
    }
}