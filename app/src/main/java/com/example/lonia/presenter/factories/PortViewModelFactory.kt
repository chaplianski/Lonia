package com.example.lonia.presenter.factories

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.lonia.domain.usecase.GetPortUseCase
import com.example.lonia.presenter.viewmodel.PortViewModel
import javax.inject.Inject

@Suppress("UNCHECKED_CAST")
class PortViewModelFactory @Inject constructor(private val getPortUseCase: GetPortUseCase) :
    ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return PortViewModel(getPortUseCase) as T
    }
}