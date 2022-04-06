package com.example.testtask.presenter.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.testtask.domain.model.Answers
import com.example.testtask.domain.usecase.GetAnswersUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

class AnswersViewModel @Inject constructor(private val getAnswersUseCase: GetAnswersUseCase): ViewModel() {

    val _answersList = MutableLiveData<List<Answers>>()
    val answersList: LiveData<List<Answers>> get() = _answersList

    fun getAnswers(briefcaseId: Long){
        viewModelScope.launch(Dispatchers.IO) {
            val list = getAnswersUseCase.execute(briefcaseId)
            _answersList.postValue(list)
        }

    }


}