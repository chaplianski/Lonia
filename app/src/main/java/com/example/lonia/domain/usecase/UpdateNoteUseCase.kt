package com.example.lonia.domain.usecase

import com.example.lonia.domain.model.Notes
import com.example.lonia.domain.repository.NoteRepository
import javax.inject.Inject

class UpdateNoteUseCase @Inject constructor(private val noteRepository: NoteRepository) {

    fun execute (note: Notes){
        noteRepository.update(note)
    }
}