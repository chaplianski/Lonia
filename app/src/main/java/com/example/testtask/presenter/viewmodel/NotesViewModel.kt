package com.example.testtask.presenter.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.testtask.domain.model.Notes
import com.example.testtask.domain.usecase.GetNotesUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

class NotesViewModel @Inject constructor(private val getNotesUseCase: GetNotesUseCase): ViewModel() {

    val _notesList = MutableLiveData<List<Notes>>()
    val notesList: LiveData<List<Notes>> get() = _notesList


    fun getNotes (briefCaseId: Long){
        viewModelScope.launch(Dispatchers.IO) {
            val list = getNotesUseCase.execute(briefCaseId)
            _notesList.postValue(list)
        }
    }




}