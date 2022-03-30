package com.example.testtask.data.storage.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.testtask.data.storage.dao.BriefCaseDao
import com.example.testtask.data.storage.model.BriefCaseData

@Database(entities = [BriefCaseData::class],version = 1,exportSchema = false)
abstract class BriefCaseDB: RoomDatabase() {
    abstract fun BriefCaseDao(): BriefCaseDao

    companion object{
        @Volatile
        private var INSTANCE: BriefCaseDB? = null

        fun getDatabase(context: Context): BriefCaseDB {
            val tempInstance = INSTANCE
            if (tempInstance != null){
                return tempInstance
            }
            synchronized(this){
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    BriefCaseDB::class.java,
                    "briefcase_db"
                ).build()
                INSTANCE = instance
                return instance
            }
        }
    }
}