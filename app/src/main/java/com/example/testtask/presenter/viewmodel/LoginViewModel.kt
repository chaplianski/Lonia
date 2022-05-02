package com.example.testtask.presenter.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.testtask.domain.exceptions.InternetConnectionException
import com.example.testtask.domain.exceptions.NetworkException
import com.example.testtask.domain.model.LoginRequest
import com.example.testtask.domain.model.LoginResponse
import com.example.testtask.domain.usecase.getTokenUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

class LoginViewModel @Inject constructor(private val getTokenUseCase: getTokenUseCase): ViewModel() {


    val _loginErrorMessage = MutableLiveData<String>()
    val loginErrorMessage: LiveData<String> get() = _loginErrorMessage

    fun getToken (loginRequest: LoginRequest) {
       viewModelScope.launch(Dispatchers.IO) {
       //    val response = getTokenUseCase.execute(loginRequest)
       //    Log.d("My Log", "Login viewModel Token^ $response")

           getTokenUseCase.execute(loginRequest).fold({
               val netExeptionMessage = "access is allowed"
               _loginErrorMessage.postValue(netExeptionMessage)

           }, {
               when (it) {
                   is NetworkException -> {
                       val netExeptionMessage = "Network error"
                       _loginErrorMessage.postValue(netExeptionMessage)

                   }
                   is InternetConnectionException -> {
                       val netExeptionMessage = "Internet connection error"
                       _loginErrorMessage.postValue(netExeptionMessage)

                   }
                   else -> {
                       val netExeptionMessage = "Unknown error"
                       _loginErrorMessage.postValue(netExeptionMessage)

                   }
               }
           }
           )
       //    _loginResponse.postValue(response)
       }

    }
}