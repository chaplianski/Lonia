package com.example.lonia.data.storage.storagies

import com.example.lonia.data.storage.model.NotesData

interface NoteStorage {

    fun addNote (noteData: NotesData)

    fun getNotes(briefcaseId: Long): List<NotesData>

    fun updateNote(noteData: NotesData)
}