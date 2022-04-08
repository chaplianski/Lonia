package com.example.testtask.presenter.factories

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.testtask.domain.usecase.GetInspectionTypeUseCase
import com.example.testtask.presenter.viewmodel.InspectionTypeViewModel
import javax.inject.Inject

@Suppress("UNCHECKED_CAST")
class InspectionTypeViewModelFactory @Inject constructor(private val getInspectionTypeUseCase: GetInspectionTypeUseCase) :
    ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return InspectionTypeViewModel(getInspectionTypeUseCase) as T
    }


}