package com.example.testtask.presenter.factories

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.testtask.domain.usecase.GetVesselsUseCase
import com.example.testtask.presenter.viewmodel.VesselsViewModel
import javax.inject.Inject

@Suppress("UNCHECKED_CAST")
class VesselViewModelFactory @Inject constructor(private val getVasselsUseCase: GetVesselsUseCase):
    ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return VesselsViewModel (getVasselsUseCase) as T
    }

}