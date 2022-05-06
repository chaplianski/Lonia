package com.example.lonia.data.storage.database

import com.example.lonia.data.storage.dao.BriefcaseDao
import com.example.lonia.data.storage.model.NotesData
import com.example.lonia.data.storage.storagies.NoteStorage
import javax.inject.Inject

class NoteStorageImpl @Inject constructor() : NoteStorage {

    @Inject
    lateinit var briefcaseDao: BriefcaseDao

    override fun addNote(noteData: NotesData) {
        briefcaseDao.insertNote(noteData)
    }

    override fun getNotes(briefcaseId: Long): List<NotesData> {
        return briefcaseDao.getNotes(briefcaseId)
    }

    override fun updateNote(noteData: NotesData) {
        briefcaseDao.updateNotes(noteData)
    }


}