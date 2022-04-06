package com.example.testtask.presenter.factories

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.testtask.domain.usecase.AddAnswerUseCase
import com.example.testtask.domain.usecase.GetAnswerUseCase
import com.example.testtask.domain.usecase.UpdateQuestionsUseCase
import com.example.testtask.presenter.viewmodel.AnswerViewModel
import javax.inject.Inject

@Suppress("UNCHECKED_CAST")
class AnswerViewModelFactory @Inject constructor(
    private val getAnswerUseCase: GetAnswerUseCase,
    private val addAnswerUseCase: AddAnswerUseCase,
    private val updateQuestionsUseCase: UpdateQuestionsUseCase
) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return AnswerViewModel(getAnswerUseCase, addAnswerUseCase, updateQuestionsUseCase) as T
    }

}