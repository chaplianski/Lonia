package com.example.lonia.presenter.factories

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.lonia.domain.usecase.getTokenUseCase
import com.example.lonia.presenter.viewmodel.LoginViewModel
import javax.inject.Inject

@Suppress ("UNCHECKED_CAST")
class LoginViewModelFactory @Inject constructor(private val getTokenUseCase: getTokenUseCase): ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return LoginViewModel(getTokenUseCase) as T
    }
}