package com.example.testtask.presenter.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.testtask.domain.model.Answers
import com.example.testtask.domain.model.Photos
import com.example.testtask.domain.model.Questions
import com.example.testtask.domain.usecase.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.cancel
import kotlinx.coroutines.launch
import javax.inject.Inject

class AnswerViewModel @Inject constructor(
    private val getAnswerUseCase: GetAnswerUseCase,
    private val updateQuestionsUseCase: UpdateQuestionsUseCase,
    private val updateAnswerUseCase: UpdateAnswerUseCase,
    private val getPhotosUseCase: GetPhotosUseCase,
    private val updatePhotosUseCase: UpdatePhotosUseCase,
    private val insertPhotoUseCase: InsertPhotoUseCase,
    private val deletePhotoUseCase: DeletePhotoUseCase,
    private val updateListQuestionsUseCase: UpdateListQuestionsUseCase
) : ViewModel() {

    val _answer = MutableLiveData<Answers>()
    val answer: LiveData<Answers> get() = _answer
    val _photos = MutableLiveData<List<Photos>>()
    val photos: LiveData<List<Photos>> get() = _photos

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

    fun updateListQuestionsAndAddAnswer(questionsIdList: List<String>, answers: Answers){
        viewModelScope.launch(Dispatchers.IO) {
            updateListQuestionsUseCase.execute(questionsIdList, answers)
        }

    }

    fun getPhotos(idAnswer: Long) {
        viewModelScope.launch(Dispatchers.IO) {
            val responsePhotos = getPhotosUseCase.execute(idAnswer)
            _photos.postValue(responsePhotos)
        }
    }

    fun updatePhotos(photos: Photos) {
        viewModelScope.launch (Dispatchers.IO) {

        }
    }

    fun insertPhoto(photo: Photos) {
        viewModelScope.launch (Dispatchers.IO){
            insertPhotoUseCase.execute(photo)
        }
    }

    fun delitePhoto(photo: Photos) {
        viewModelScope.launch (Dispatchers.IO){
            deletePhotoUseCase.execute(photo)
        }
    }

    override fun onCleared() {
        viewModelScope.cancel()
    }
}