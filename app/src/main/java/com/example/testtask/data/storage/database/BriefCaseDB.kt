package com.example.testtask.data.storage.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.testtask.data.storage.dao.BriefcaseDao
import com.example.testtask.data.storage.model.*

@Database(
    entities = [BriefCaseData::class, QuestionsData::class, AnswersData::class, PhotosData::class, NotesData::class],
    version = 1,
    exportSchema = false
)
abstract class BriefCaseDB : RoomDatabase() {

    abstract fun BriefCaseDao(): BriefcaseDao
}