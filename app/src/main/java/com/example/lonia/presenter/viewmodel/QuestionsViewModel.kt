package com.example.lonia.presenter.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.lonia.domain.usecase.GetNotAsweredQuestionsUseCase
import com.example.lonia.presenter.adapter.QuestionsExpanbleAdapter
import com.example.lonia.presenter.ui.dialogs.CategoryFilterHelper
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

class QuestionsViewModel @Inject constructor(private val getNotAsweredQuestionsUseCase: GetNotAsweredQuestionsUseCase) :
    ViewModel() {

    val _questionsList = MutableLiveData<QuestionsExpanbleAdapter>()
    val questionList: LiveData<QuestionsExpanbleAdapter> get() = _questionsList

    fun getQuestionList(briefcaseId: Long, isCheck: Boolean) {
        viewModelScope.launch(Dispatchers.IO) {
            val listQuestions = getNotAsweredQuestionsUseCase.execute(briefcaseId)

            val mapQuestionsAndCategory = CategoryFilterHelper.getQuestionsAndCategory(listQuestions)
            val listCategories = CategoryFilterHelper.getCategoryList(mapQuestionsAndCategory)
            val adapter = QuestionsExpanbleAdapter (listCategories, mapQuestionsAndCategory, isCheck)
            _questionsList.postValue(adapter)

        }
    }




}



