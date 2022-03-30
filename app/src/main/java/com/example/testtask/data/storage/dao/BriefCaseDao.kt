package com.example.testtask.data.storage.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.testtask.data.storage.model.BriefCaseData

@Dao
interface BriefCaseDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertBriefCase(briefCaseData: BriefCaseData)

//    @Query("DELETE FROM questions")
//    fun deleteAll ()

    @Query("SELECT * FROM briefcase")
    fun getAllBriefCases(): List<BriefCaseData>

    @Query("SELECT * FROM briefcase WHERE briefCaseId= :id")
    fun getBriefCase(id: Int): BriefCaseData

//    @Query("SELECT questionId, question, answer FROM questions WHERE questionId= :id")
//    fun getBodyQuestion(id: Int): Questions
}