package com.example.testtask.presenter.factories

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.testtask.domain.usecase.GetAllBriefCasesUseCase
import com.example.testtask.presenter.viewmodel.BriefCaseViewModel
import javax.inject.Inject

@Suppress ("UNCHECKED_CAST")
class BriefcaseViewModelFactory @Inject constructor(private val getAllBriefCasesUseCase: GetAllBriefCasesUseCase): ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return BriefCaseViewModel(getAllBriefCasesUseCase) as T
    }


}