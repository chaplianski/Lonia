package com.example.lonia.presenter.viewmodel

import androidx.annotation.StringRes
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.lonia.domain.exceptions.InternetConnectionException
import com.example.lonia.domain.exceptions.NetworkException
import com.example.lonia.domain.model.Questionnaires
import com.example.lonia.domain.usecase.GetVesselsUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.cancel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

class VesselsViewModel @Inject constructor(private val getVasselsUseCase: GetVesselsUseCase) :
    ViewModel() {

    val _vassels = MutableLiveData<List<String>>()
    val vassels: LiveData<List<String>> get() = _vassels


    private val screenStateData = MutableStateFlow<VesselState>(
        VesselState.Loading
    )
    val screenState = screenStateData.asStateFlow()

     suspend fun getListVassels() {

         getVasselsUseCase.execute().fold({
             screenStateData.emit(VesselState.Success(it))
         }, {
             when (it) {
                 is NetworkException -> {
                     screenStateData.emit(VesselState.Error(it.errorMessage))
                 }
                 is InternetConnectionException -> {
                     screenStateData.emit(VesselState.Error(it.errorMessage))
                 }
                 else -> {}
             }
         })





//        viewModelScope.launch(Dispatchers.IO) {
//            val vasselsList = getVasselsUseCase.execute()
//            _vassels.postValue(vasselsList)
//        }
    }

    override fun onCleared() {
        viewModelScope.cancel()
    }

    sealed class VesselState() {
//        object DownWork : VesselState()
        data class Error(@StringRes val exception: Int) : VesselState()
        data class Success(val vessels: List<String>) : VesselState()
        object Loading : VesselState()
    }

}