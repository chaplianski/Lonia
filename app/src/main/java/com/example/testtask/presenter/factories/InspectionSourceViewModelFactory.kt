package com.example.testtask.presenter.factories

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.testtask.domain.usecase.GetInspectionSourceUseCase
import com.example.testtask.domain.usecase.GetInspectionTypeUseCase
import com.example.testtask.presenter.viewmodel.InspectionSourceViewModel
import javax.inject.Inject

@Suppress("UNCHECKED_CAST")
class InspectionSourceViewModelFactory @Inject constructor(private val getInspectionSourceUseCase: GetInspectionSourceUseCase) :
    ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return InspectionSourceViewModel(getInspectionSourceUseCase) as T
    }
}