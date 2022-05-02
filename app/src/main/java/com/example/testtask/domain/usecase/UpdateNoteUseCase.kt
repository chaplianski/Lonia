package com.example.testtask.domain.usecase

import com.example.testtask.domain.model.Notes
import com.example.testtask.domain.repository.NoteRepository
import javax.inject.Inject

class UpdateNoteUseCase @Inject constructor(private val noteRepository: NoteRepository) {

    fun execute (note: Notes){
        noteRepository.update(note)
    }
}