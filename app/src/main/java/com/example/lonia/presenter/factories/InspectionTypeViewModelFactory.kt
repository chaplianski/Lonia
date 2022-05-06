package com.example.lonia.presenter.factories

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.lonia.domain.usecase.GetInspectionTypeUseCase
import com.example.lonia.presenter.viewmodel.InspectionTypeViewModel
import javax.inject.Inject

@Suppress("UNCHECKED_CAST")
class InspectionTypeViewModelFactory @Inject constructor(private val getInspectionTypeUseCase: GetInspectionTypeUseCase) :
    ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return InspectionTypeViewModel(getInspectionTypeUseCase) as T
    }


}