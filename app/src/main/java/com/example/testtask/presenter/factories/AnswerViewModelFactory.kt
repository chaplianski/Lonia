package com.example.testtask.presenter.factories

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.testtask.domain.usecase.*
import com.example.testtask.presenter.viewmodel.AnswerViewModel
import javax.inject.Inject

@Suppress("UNCHECKED_CAST")
class AnswerViewModelFactory @Inject constructor(
    private val getAnswerUseCase: GetAnswerUseCase,
    private val updateQuestionsUseCase: UpdateQuestionsUseCase,
    private val updateAnswerUseCase: UpdateAnswerUseCase,
    private val getPhotosUseCase: GetPhotosUseCase,
    private val updatePhotosUseCase: UpdatePhotosUseCase,
    private val insertPhotoUseCase: InsertPhotoUseCase,
    private val deletePhotoUseCase: DeletePhotoUseCase,
    private val updateListQuestionsUseCase: UpdateListQuestionsUseCase
) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return AnswerViewModel(
            getAnswerUseCase,
            updateQuestionsUseCase,
            updateAnswerUseCase,
            getPhotosUseCase,
            updatePhotosUseCase,
            insertPhotoUseCase,
            deletePhotoUseCase,
            updateListQuestionsUseCase
        ) as T
    }

}