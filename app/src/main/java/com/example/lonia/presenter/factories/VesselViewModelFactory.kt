package com.example.lonia.presenter.factories

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.lonia.domain.usecase.GetVesselsUseCase
import com.example.lonia.presenter.viewmodel.VesselsViewModel
import javax.inject.Inject

@Suppress("UNCHECKED_CAST")
class VesselViewModelFactory @Inject constructor(private val getVasselsUseCase: GetVesselsUseCase) :
    ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return VesselsViewModel(getVasselsUseCase) as T
    }

}