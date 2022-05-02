package com.example.testtask.data.storage.storagies

import com.example.testtask.data.storage.model.NotesData

interface NoteStorage {

    fun addNote (noteData: NotesData)

    fun getNotes(briefcaseId: Long): List<NotesData>

    fun updateNote(noteData: NotesData)
}