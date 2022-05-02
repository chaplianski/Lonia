package com.example.testtask.presenter.factories

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.testtask.domain.usecase.AddNoteUseCase
import com.example.testtask.domain.usecase.UpdateNoteUseCase
import com.example.testtask.presenter.viewmodel.NoteViewModel
import javax.inject.Inject


@Suppress("UNCHECKED_CAST")
class NoteViewModelFactory @Inject constructor(
    private val addNoteUseCase: AddNoteUseCase,
    private val updateNoteUseCase: UpdateNoteUseCase
) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return NoteViewModel(addNoteUseCase, updateNoteUseCase) as T
    }

}