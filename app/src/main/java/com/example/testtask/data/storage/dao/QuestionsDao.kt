package com.example.testtask.data.storage.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import com.example.testtask.data.storage.model.QuestionsData

@Dao
interface QuestionsDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertQuestions(questionsData: QuestionsData)

 //   @Query("SELECT * FROM questionsData")
 //   fun getAllQuestions(): List<QuestionsData>
}