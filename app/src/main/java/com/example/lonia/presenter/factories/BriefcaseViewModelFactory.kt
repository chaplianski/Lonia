package com.example.lonia.presenter.factories

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.lonia.domain.usecase.GetAllBriefCasesUseCase
import com.example.lonia.domain.usecase.GetQuestionsUseCase
import com.example.lonia.presenter.viewmodel.BriefCaseViewModel
import javax.inject.Inject

@Suppress("UNCHECKED_CAST")
class BriefcaseViewModelFactory @Inject constructor(
    private val getAllBriefCasesUseCase: GetAllBriefCasesUseCase,
    private val getQuestionsUseCase: GetQuestionsUseCase
) :
    ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return BriefCaseViewModel(getAllBriefCasesUseCase, getQuestionsUseCase) as T
    }


}