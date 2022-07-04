package com.example.lonia.presenter.viewmodel

import androidx.annotation.StringRes
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.lonia.domain.exceptions.InternetConnectionException
import com.example.lonia.domain.exceptions.NetworkException
import com.example.lonia.domain.usecase.GetPortUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.cancel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

class PortViewModel @Inject constructor(
    private val getPortUseCase: GetPortUseCase
    ): ViewModel() {

        val _portList = MutableLiveData<List<String>>()
        val portList: LiveData<List<String>> get() = _portList

    val screenStateData = MutableStateFlow<PortState>(
        PortState.Loading
    )
    val screenState = screenStateData.asStateFlow()

    suspend fun getPortList () {

            getPortUseCase.execute().fold({
                screenStateData.emit(PortState.Success(it))
            }, {
                when (it) {
                    is NetworkException -> {
                        screenStateData.emit(PortState.Error(it.errorMessage))
                    }
                    is InternetConnectionException -> {
                        screenStateData.emit(PortState.Error(it.errorMessage))
                    }
                    else -> {}
                }
            })


//        viewModelScope.launch(Dispatchers.IO) {
//            val ports = getPortUseCase.execute()
//            _portList.postValue(ports)
        }


    override fun onCleared() {
        viewModelScope.cancel()
    }

    sealed class PortState() {
        data class Error(@StringRes val exception: Int) : PortState()
        data class Success(val ports: List<String>) : PortState()
        object Loading : PortState()
    }
}