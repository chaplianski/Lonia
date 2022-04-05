package com.example.testtask.presenter.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.testtask.domain.model.Answers
import com.example.testtask.domain.usecase.AddAnswerUseCase
import com.example.testtask.domain.usecase.GetAnswerUseCase
import javax.inject.Inject

class AnswerViewModel @Inject constructor(
    private val getAnswerUseCase: GetAnswerUseCase,
    private val addAnswerUseCase: AddAnswerUseCase
) : ViewModel() {


    val _answer = MutableLiveData<Answers>()
    val answer: LiveData<Answers> get() = _answer

    fun getAnswer(id: Int): Answers {
        return getAnswerUseCase.execute(id)
    }

    fun addAnswer(answers: Answers) {
        addAnswerUseCase.execute(answers)
    }


}