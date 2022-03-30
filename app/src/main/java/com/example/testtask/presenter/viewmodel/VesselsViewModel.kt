package com.example.testtask.presenter.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.testtask.domain.model.BriefCase
import com.example.testtask.domain.usecase.GetVesselsUseCase
import javax.inject.Inject

class VesselsViewModel @Inject constructor(getVasselsUseCase: GetVesselsUseCase): ViewModel() {

    val _vassels = MutableLiveData<List<String>>()
    val vassels: LiveData<List<String>> get() = _vassels
    val getUsecase = getVasselsUseCase

    fun getListVassels() {
        val vasselsList = getUsecase.execute()
        _vassels.postValue(vasselsList)
    }

}