package com.example.testtask.presenter.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.testtask.domain.model.BriefCase
import com.example.testtask.domain.usecase.GetAllBriefCasesUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

class BriefCaseViewModel @Inject constructor(
    private val getAllBriefCasesUseCase: GetAllBriefCasesUseCase): ViewModel() {

    val _briefCase = MutableLiveData<List<BriefCase>>()
    val briefCase: LiveData<List<BriefCase>> get() = _briefCase

    fun getBriefCaseList(){
       viewModelScope.launch(Dispatchers.IO) {
           val briefCaseList = getAllBriefCasesUseCase.execute()
           _briefCase.postValue(briefCaseList)
       }
    }
}