package com.example.lonia.presenter.viewmodel

import androidx.annotation.StringRes
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.lonia.domain.exceptions.InternetConnectionException
import com.example.lonia.domain.exceptions.NetworkException
import com.example.lonia.domain.usecase.GetInspectionSourceUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.cancel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

class InspectionSourceViewModel @Inject constructor(private val getInspectionSourceUseCase: GetInspectionSourceUseCase): ViewModel() {

    val _inspectionSourceList = MutableLiveData<List<String>>()
    val inspectionSourceList: LiveData<List<String>> get() = _inspectionSourceList

    private val screenStateData = MutableStateFlow<InspectionSourceState>(
            InspectionSourceState.Loading
        )
        val screenState = screenStateData.asStateFlow()

        suspend fun getInspectionSourceList() {

            getInspectionSourceUseCase.execute().fold({
                screenStateData.emit(InspectionSourceState.Success(it))
            }, {
                when (it) {
                    is NetworkException -> {
                        screenStateData.emit(InspectionSourceState.Error(it.errorMessage))
                    }
                    is InternetConnectionException -> {
                        screenStateData.emit(InspectionSourceState.Error(it.errorMessage))
                    }
                    else -> {}
                }
            })

//
//        viewModelScope.launch(Dispatchers.IO) {
//            val list = getInspectionSourceUseCase.execute()
//            _inspectionSourceList.postValue(list)
//        }
    }

    override fun onCleared() {
        viewModelScope.cancel()
    }

    sealed class InspectionSourceState() {
        data class Error(@StringRes val exception: Int) : InspectionSourceState()
        data class Success(val inspectionSource: List<String>) : InspectionSourceState()
        object Loading : InspectionSourceState()
    }
}