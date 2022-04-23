package com.example.testtask.presenter.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.testtask.domain.model.LoginRequest
import com.example.testtask.domain.model.LoginResponse
import com.example.testtask.domain.usecase.getTokenUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

class LoginViewModel @Inject constructor(private val getTokenUseCase: getTokenUseCase): ViewModel() {


    val _loginResponse = MutableLiveData<LoginResponse>()
    val loginResponse: LiveData<LoginResponse> get() = _loginResponse

    fun getToken (loginRequest: LoginRequest){
       viewModelScope.launch(Dispatchers.IO) {
           val response = getTokenUseCase.execute(loginRequest)
           Log.d("My Log", "Login viewModel Token^ $response")

       //    _loginResponse.postValue(response)
       }

    }
}