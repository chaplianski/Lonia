package com.example.lonia.presenter.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.lonia.domain.exceptions.InternetConnectionException
import com.example.lonia.domain.exceptions.NetworkException
import com.example.lonia.domain.model.LoginRequest
import com.example.lonia.domain.usecase.getTokenUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.cancel
import kotlinx.coroutines.launch
import javax.inject.Inject

class LoginViewModel @Inject constructor(private val getTokenUseCase: getTokenUseCase) :
    ViewModel() {


    val _loginErrorMessage = MutableLiveData<String>()
    val loginErrorMessage: LiveData<String> get() = _loginErrorMessage

    fun getToken(loginRequest: LoginRequest) {
        viewModelScope.launch(Dispatchers.IO) {

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
        }
    }

    override fun onCleared() {
        viewModelScope.cancel()
    }
}