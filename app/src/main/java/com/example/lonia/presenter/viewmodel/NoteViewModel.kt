package com.example.lonia.presenter.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.lonia.domain.model.Notes
import com.example.lonia.domain.usecase.AddNoteUseCase
import com.example.lonia.domain.usecase.UpdateNoteUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

class NoteViewModel @Inject constructor(
    private val addNoteUseCase: AddNoteUseCase,
    private val updateNoteUseCase: UpdateNoteUseCase
) : ViewModel() {

    fun addNote(notes: Notes) {
        viewModelScope.launch(Dispatchers.IO) {
            addNoteUseCase.execute(notes)
        }
    }

    fun updateNote(notes: Notes){
        viewModelScope.launch(Dispatchers.IO) {
            updateNoteUseCase.execute(notes)
        }

    }


}