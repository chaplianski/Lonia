package com.example.lonia.presenter.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.lonia.domain.model.Questions
import com.example.lonia.domain.usecase.GetAnswersUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

class AnswersViewModel @Inject constructor(private val getAnswersUseCase: GetAnswersUseCase): ViewModel() {

    val _answersList = MutableLiveData<List<Questions>>()
    val answersList: LiveData<List<Questions>> get() = _answersList

    fun getAnswers(briefcaseId: Long){
        viewModelScope.launch(Dispatchers.IO) {
            val list = getAnswersUseCase.execute(briefcaseId)
            _answersList.postValue(list)
        }
    }
}