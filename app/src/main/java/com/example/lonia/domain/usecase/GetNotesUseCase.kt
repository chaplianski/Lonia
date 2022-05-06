package com.example.lonia.domain.usecase

import com.example.lonia.domain.model.Notes
import com.example.lonia.domain.repository.NoteRepository
import javax.inject.Inject

class GetNotesUseCase @Inject constructor(private val notesRepository: NoteRepository) {

    fun execute(briefcaseId: Long) : List<Notes>{
        return  notesRepository.getNotes(briefcaseId)
    }
}