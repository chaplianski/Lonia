package com.example.testtask.domain.usecase

import com.example.testtask.domain.model.Notes
import com.example.testtask.domain.repository.NoteRepository
import javax.inject.Inject

class GetNotesUseCase @Inject constructor(private val notesRepository: NoteRepository) {

    fun execute(briefcaseId: Long) : List<Notes>{
        return  notesRepository.getNotes(briefcaseId)
    }
}