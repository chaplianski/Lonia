package com.example.testtask.presenter.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.testtask.domain.usecase.GetInspectionSourceUseCase
import com.example.testtask.domain.usecase.GetInspectionTypeUseCase
import javax.inject.Inject

class InspectionSourceViewModel @Inject constructor(private val getInspectionSourceUseCase: GetInspectionSourceUseCase): ViewModel() {

    val _inspectionSourceList = MutableLiveData<List<String>>()
    val inspectionSourceList: LiveData<List<String>> get() = _inspectionSourceList

    fun getInspectionSourceList(){
        val list = getInspectionSourceUseCase.execute()
        _inspectionSourceList.postValue(list)
    }
}