package com.example.testtask.data.storage.dao

import android.util.Log
import androidx.room.*
import com.example.testtask.data.storage.model.AnswersData
import com.example.testtask.data.storage.model.BriefCaseData
import com.example.testtask.data.storage.model.PhotosData
import com.example.testtask.data.storage.model.QuestionsData

@Dao
abstract class BriefcaseDao {

    @Query("SELECT * FROM briefcase WHERE briefCaseId= :id")
    abstract fun getBriefCase(id: Long): BriefCaseData

    @Query("SELECT * FROM answers WHERE answerId= :answerId")
    abstract fun getAnswer(answerId: Long): AnswersData

    @Query("SELECT * FROM questions WHERE questions.briefCaseId = :briefcaseId AND questions.isAnswered = 1 ")
    abstract fun getAnsweredQuestions(briefcaseId: Long): List<QuestionsData>

    @Query("SELECT * FROM briefcase")
    abstract fun getAllBriefCases(): List<BriefCaseData>

    @Query("SELECT * FROM questions WHERE briefCaseId= :id")
    abstract fun getAllQuestions(id: Long): List<QuestionsData>

    @Query("SELECT * FROM photos WHERE answerId = :answerId")
    abstract fun getPhotos(answerId: Long): List<PhotosData>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    abstract fun insertAllQuestions(questionsDataList: List<QuestionsData>)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    abstract fun insertBriefcase(briefCaseData: BriefCaseData): Long

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    abstract fun insertAnswer(answersData: AnswersData): Long

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    abstract fun insertPhoto(photosData: PhotosData): Long

    fun insert(briefCaseData: BriefCaseData, questionsDataList: List<QuestionsData>) {
        val briefcaseId: Long = insertBriefcase(briefCaseData)
        val questions = questionsDataList
        for (question in questions) {
            question.briefCaseId = briefcaseId
        }
        insertAllQuestions(questions)
    }

    @Update
    abstract fun updateAnswer(answersData: AnswersData)

    @Update
    abstract fun updatePhotos(photosData: PhotosData)

    @Query("UPDATE questions SET answer= :answerId, isAnswered= :isAnswered  WHERE questionid = :questionId")
    abstract fun updateQuestion(answerId: Long, isAnswered: Boolean, questionId: String)

    fun updateQuestionsAndInsertAnswer(questionsData: QuestionsData, answersData: AnswersData) {
        val answerId = insertAnswer(answersData)
        val isAnswered = true
        val questionId = questionsData.questionid
        updateQuestion(answerId, isAnswered, questionId)
    }



}