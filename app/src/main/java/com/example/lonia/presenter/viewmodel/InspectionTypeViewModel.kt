package com.example.lonia.presenter.viewmodel

import androidx.annotation.StringRes
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.lonia.domain.exceptions.InternetConnectionException
import com.example.lonia.domain.exceptions.NetworkException
import com.example.lonia.domain.usecase.GetInspectionTypeUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.cancel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

class InspectionTypeViewModel @Inject constructor(private val getInspectionTypeUseCase: GetInspectionTypeUseCase): ViewModel() {

    val _inspectionTypeList = MutableLiveData<List<String>>()
    val inspectionTypeList: LiveData<List<String>> get() = _inspectionTypeList

    private val screenStateData = MutableStateFlow<InspectionTypeState>(
        InspectionTypeState.Loading
    )
    val screenState = screenStateData.asStateFlow()

    suspend fun getInspectionTypeList() {

            getInspectionTypeUseCase.execute().fold({
                screenStateData.emit(InspectionTypeState.Success(it))
            }, {
                when (it) {
                    is NetworkException -> {
                        screenStateData.emit(InspectionTypeState.Error(it.errorMessage))
                    }
                    is InternetConnectionException -> {
                        screenStateData.emit(InspectionTypeState.Error(it.errorMessage))
                    }
                    else -> {}
                }
            })


//        viewModelScope.launch(Dispatchers.IO) {
//            val list = getInspectionTypeUseCase.execute()
//            _inspectionTypeList.postValue(list)
//        }
    }

    override fun onCleared() {
        viewModelScope.cancel()
    }

    sealed class InspectionTypeState() {
        data class Error(@StringRes val exception: Int) : InspectionTypeState()
        data class Success(val inspectionType: List<String>) : InspectionTypeState()
        object Loading : InspectionTypeState()
    }
}