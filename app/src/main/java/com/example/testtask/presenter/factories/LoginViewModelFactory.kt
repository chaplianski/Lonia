package com.example.testtask.presenter.factories

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.testtask.domain.usecase.getTokenUseCase
import com.example.testtask.presenter.viewmodel.LoginViewModel
import javax.inject.Inject

@Suppress ("UNCHECKED_CAST")
class LoginViewModelFactory @Inject constructor(private val getTokenUseCase: getTokenUseCase): ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return LoginViewModel(getTokenUseCase) as T
    }
}