package com.example.testtask.data.storage.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.testtask.data.storage.dao.BriefcaseDao
import com.example.testtask.data.storage.dao.QuestionsDao
import com.example.testtask.data.storage.model.AnswersData
import com.example.testtask.data.storage.model.BriefCaseData
import com.example.testtask.data.storage.model.QuestionsData

@Database(entities = [BriefCaseData::class, QuestionsData::class, AnswersData::class],version = 1,exportSchema = false)
abstract class BriefCaseDB: RoomDatabase()  {

    abstract fun BriefCaseDao(): BriefcaseDao
}