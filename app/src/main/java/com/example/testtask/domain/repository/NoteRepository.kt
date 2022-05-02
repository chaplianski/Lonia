package com.example.testtask.domain.repository

import com.example.testtask.domain.model.Notes

interface NoteRepository {

    fun addNote (note: Notes)

    fun getNotes(briefcaseId: Long): List<Notes>

    fun  update (note: Notes)
}