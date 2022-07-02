package com.example.lonia.presenter.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.lonia.domain.usecase.GetInspectionSourceUseCase
import kotlinx.coroutines.cancel
import javax.inject.Inject

class InspectionSourceViewModel @Inject constructor(private val getInspectionSourceUseCase: GetInspectionSourceUseCase): ViewModel() {

    val _inspectionSourceList = MutableLiveData<List<String>>()
    val inspectionSourceList: LiveData<List<String>> get() = _inspectionSourceList

    fun getInspectionSourceList(){
        val list = getInspectionSourceUseCase.execute()
        _inspectionSourceList.postValue(list)
    }

    override fun onCleared() {
        viewModelScope.cancel()
    }
}