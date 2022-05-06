package com.example.lonia.presenter.factories

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.lonia.domain.usecase.AddNoteUseCase
import com.example.lonia.domain.usecase.UpdateNoteUseCase
import com.example.lonia.presenter.viewmodel.NoteViewModel
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