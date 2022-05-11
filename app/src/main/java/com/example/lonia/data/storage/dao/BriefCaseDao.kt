package com.example.lonia.data.storage.dao

import androidx.room.*
import com.example.lonia.data.storage.model.*

@Dao
abstract class BriefcaseDao {

    @Query("SELECT * FROM briefcase WHERE briefCaseId= :id")
    abstract fun getBriefCase(id: Long): BriefCaseData

    @Query("SELECT * FROM answers WHERE answerId= :answerId")
    abstract fun getAnswer(answerId: Long): AnswersData

    @Query("SELECT * FROM questions WHERE questionid= :questionId")
    abstract fun getQuestion(questionId: String): QuestionsData

    @Query("SELECT * FROM questions WHERE questions.briefCaseId = :briefcaseId AND questions.isAnswered = 1 ")
    abstract fun getAnsweredQuestions(briefcaseId: Long): List<QuestionsData>

    @Query("SELECT * FROM briefcase")
    abstract fun getAllBriefCases(): List<BriefCaseData>

    @Query("SELECT * FROM questions WHERE briefCaseId= :id ")
    abstract fun getAllQuestions(id: Long): List<QuestionsData>

    @Query("SELECT * FROM questions WHERE briefCaseId= :id AND questions.isAnswered = 0 ")
    abstract fun getNotAnsweredQuestions(id: Long): List<QuestionsData>

    @Query("SELECT * FROM photos WHERE questionId = :questionId")
    abstract fun getPhotos(questionId: String): List<PhotosData>

    @Query("SELECT * FROM notes WHERE briefcaseId = :briefcaseId")
    abstract fun getNotes(briefcaseId: Long): List<NotesData>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    abstract fun insertAllQuestions(questionsDataList: List<QuestionsData>)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    abstract fun insertBriefcase(briefCaseData: BriefCaseData): Long

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    abstract fun insertAnswer(answersData: AnswersData): Long

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    abstract fun insertNote(notesData: NotesData)

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

    @Delete
    abstract fun deletePhotos(photosData: PhotosData)

    @Update
    abstract fun updateAnswer(answersData: AnswersData)

    @Update
    abstract fun updatePhotos(photosData: PhotosData)

    @Update
    abstract fun updateNotes(notesData: NotesData)

    @Query("UPDATE questions SET answer= :answer, isAnswered= :isAnswered, dateOfInspection= :dateOfInspection, commentForQuestion= :commentForQuestion, significance= :significance  WHERE questionid = :questionId")
    abstract fun updateQuestion1(
        answer: Int,
        dateOfInspection: String,
        commentForQuestion: String,
        isAnswered: Boolean,
        questionId: String,
        significance: String
    )

    @Query("UPDATE questions SET answer= :answerId, isAnswered= :isAnswered  WHERE questionid = :questionId")
    abstract fun updateQuestion(answerId: Long, isAnswered: Boolean, questionId: String)

    fun updateOneQuestion(questionData: QuestionsData) {
        val answer = questionData.answer
        val dateOfInspection = questionData.dateOfInspection
        val commentForQuestion = questionData.commentForQuestion
        val isAnswered = questionData.isAnswered
        val questionId = questionData.questionid
        val significance = questionData.significance
        updateQuestion1(answer, dateOfInspection, commentForQuestion, isAnswered, questionId, significance)
    }

    fun updateQuestionsAndInsertAnswer(questionsData: QuestionsData, answersData: AnswersData) {
        val answerId = insertAnswer(answersData)
        val isAnswered = true
        val questionId = questionsData.questionid
        updateQuestion(answerId, isAnswered, questionId)
    }

    fun updateQuestionsListAndInsertAnswer(
        questionsListId: List<String>,
        answersData: AnswersData
    ) {
        val answerId = insertAnswer(answersData)
        val isAnswered = true
        for (questionId in questionsListId) {
            updateQuestion(answerId, isAnswered, questionId)
        }

    }


}