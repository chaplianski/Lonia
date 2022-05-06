package com.example.lonia.domain.repository

import com.example.lonia.domain.model.Notes

interface NoteRepository {

    fun addNote (note: Notes)

    fun getNotes(briefcaseId: Long): List<Notes>

    fun  update (note: Notes)
}