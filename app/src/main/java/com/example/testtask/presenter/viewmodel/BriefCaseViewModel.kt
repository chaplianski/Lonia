package com.example.testtask.presenter.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.testtask.domain.model.BriefCase
import com.example.testtask.domain.usecase.GetAllBriefCases
import com.example.testtask.domain.usecase.GetBriefCasesUseCase

class BriefCaseViewModel (
    private val getAllBriefCases: GetAllBriefCases): ViewModel() {

    val _briefCase = MutableLiveData<List<BriefCase>>()
    val briefCase: LiveData<List<BriefCase>> get() = _briefCase

    fun getBriefCaseList(){
        val briefCaseList = getAllBriefCases.execute()
        _briefCase.postValue(briefCaseList)
    }


}