package com.example.lonia.data.storage.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.lonia.data.storage.dao.BriefcaseDao
import com.example.lonia.data.storage.model.*

@Database(
    entities = [
        BriefCaseData::class,
        QuestionsData::class,
        PhotosData::class,
        NotesData::class],
    version = 1,
    exportSchema = false
)
@TypeConverters(PhotoConverters::class)

abstract class BriefCaseDB : RoomDatabase() {

    abstract fun BriefCaseDao(): BriefcaseDao
}