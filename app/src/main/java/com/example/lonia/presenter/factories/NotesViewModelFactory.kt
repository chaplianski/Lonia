package com.example.lonia.presenter.factories

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.lonia.domain.usecase.GetNotesUseCase
import com.example.lonia.presenter.viewmodel.NotesViewModel
import javax.inject.Inject

@Suppress("UNCHECKED_CAST")
class NotesViewModelFactory @Inject constructor(private val getNotesUseCase: GetNotesUseCase) :
    ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return NotesViewModel(getNotesUseCase) as T
    }
}