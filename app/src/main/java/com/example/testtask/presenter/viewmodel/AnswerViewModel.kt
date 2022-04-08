package com.example.testtask.presenter.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.testtask.domain.model.Answers
import com.example.testtask.domain.model.Questions
import com.example.testtask.domain.usecase.GetAnswerUseCase
import com.example.testtask.domain.usecase.UpdateAnswerUseCase
import com.example.testtask.domain.usecase.UpdateQuestionsUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.cancel
import kotlinx.coroutines.launch
import javax.inject.Inject

class AnswerViewModel @Inject constructor(
    private val getAnswerUseCase: GetAnswerUseCase,
    private val updateQuestionsUseCase: UpdateQuestionsUseCase,
    private val updateAnswerUseCase: UpdateAnswerUseCase
) : ViewModel() {

    val _answer = MutableLiveData<Answers>()
    val answer: LiveData<Answers> get() = _answer

    fun getAnswer(idAnswer: Long) {
        viewModelScope.launch(Dispatchers.IO) {
            val responseAnswer = getAnswerUseCase.execute(idAnswer)
            _answer.postValue(responseAnswer)
        }
    }

    fun updateAnswer(answers: Answers) {
        viewModelScope.launch(Dispatchers.IO) {
            updateAnswerUseCase.execute(answers)
        }
    }

    fun updateQuestionAndAddAnswer(questions: Questions, answers: Answers) {
        viewModelScope.launch(Dispatchers.IO) {
            updateQuestionsUseCase.execute(questions, answers)
        }
    }

    override fun onCleared() {
        viewModelScope.cancel()
    }
}