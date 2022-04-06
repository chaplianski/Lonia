package com.example.testtask.presenter.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.testtask.domain.model.Answers
import com.example.testtask.domain.model.Questions
import com.example.testtask.domain.usecase.AddAnswerUseCase
import com.example.testtask.domain.usecase.GetAnswerUseCase
import com.example.testtask.domain.usecase.UpdateQuestionsUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.cancel
import kotlinx.coroutines.launch
import javax.inject.Inject

class AnswerViewModel @Inject constructor(
    private val getAnswerUseCase: GetAnswerUseCase,
    private val addAnswerUseCase: AddAnswerUseCase,
    private val updateQuestionsUseCase: UpdateQuestionsUseCase
) : ViewModel() {


    val _answer = MutableLiveData<Answers>()
    val answer: LiveData<Answers> get() = _answer

    fun getAnswer(id: Int): Answers {
        return getAnswerUseCase.execute(id)
    }

    fun addAnswer(answers: Answers) {
        addAnswerUseCase.execute(answers)
    }

    fun updateQuestionAndAddAnswer(questions: Questions, answers: Answers){
       viewModelScope.launch(Dispatchers.IO) {
           updateQuestionsUseCase.execute(questions, answers)
       }


    }

    override fun onCleared() {
        viewModelScope.cancel()
    }
}