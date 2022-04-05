package com.example.testtask.presenter.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.testtask.domain.model.Questions
import com.example.testtask.domain.usecase.GetQuestionsUseCase
import javax.inject.Inject

class QuestionsViewModel @Inject constructor(private val getQuestionsUseCase: GetQuestionsUseCase): ViewModel() {

    val _questionsList = MutableLiveData<List<Questions>>()
    val questionList: LiveData<List<Questions>> get() = _questionsList

    fun getQuestionList (){
        val list = getQuestionsUseCase.execute()
        _questionsList.postValue(list)
    }
}