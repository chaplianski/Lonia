package com.example.testtask.data.repository

import com.example.testtask.data.storage.database.NoteStorageImpl
import com.example.testtask.domain.model.Notes
import com.example.testtask.domain.repository.NoteRepository
import javax.inject.Inject

class NoteRepositoryImpl @Inject constructor(private val noteStorage: NoteStorageImpl) : NoteRepository  {

    override fun addNote(note: Notes) {
        noteStorage.addNote(note.notesMapDomainToData())
    }

    override fun getNotes(briefcaseId: Long): List<Notes> {
        return noteStorage.getNotes(briefcaseId).map { it.notesMapDataToDomain() }
    }

    override fun update(note: Notes) {
        noteStorage.updateNote(note.notesMapDomainToData())
    }
}