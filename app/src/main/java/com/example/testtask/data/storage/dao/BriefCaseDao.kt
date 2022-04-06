package com.example.testtask.data.storage.dao

import androidx.room.*
import com.example.testtask.data.storage.model.BriefCaseData
import com.example.testtask.data.storage.model.QuestionsData
import android.provider.SyncStateContract.Helpers.insert
import android.util.Log
import com.example.testtask.data.storage.model.AnswersData
import com.example.testtask.data.storage.model.BriefcaseWithQuestions


@Dao
//interface BriefCaseDao {
public abstract class BriefcaseDao {

    @Query("SELECT * FROM briefcase")
    abstract fun getAllBriefCases(): List<BriefCaseData>

    @Query("SELECT * FROM questions WHERE briefCaseId= :id")
    abstract fun getAllQuestions(id: Long): List<QuestionsData>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    abstract fun insertAllQuestions(questionsDataList: List<QuestionsData>)

    fun insert(briefCaseData: BriefCaseData, questionsDataList: List<QuestionsData>) {

        val briefcaseId: Long = insertBriefcase(briefCaseData)
        val questions = questionsDataList
        for (question in questions) {
            question.briefCaseId = briefcaseId
        }
        insertAllQuestions(questions)
    }

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    abstract fun insertBriefcase(briefCaseData: BriefCaseData): Long

    @Query("SELECT * FROM briefcase WHERE briefCaseId= :id")
    abstract fun getBriefCase(id: Long): BriefCaseData


    fun updateQuestionsAndInsertAnswer(questionsData: QuestionsData, answersData: AnswersData) {
        val answerId = insertAnswer(answersData)
        val isAnswered = true
        val questionId = questionsData.questionid
        Log.d("My Log", "questionId in Dao: $questionId")
        updateQuestion(answerId, isAnswered, questionId)

    }

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    abstract fun insertAnswer(answersData: AnswersData): Long

    @Query("UPDATE questions SET answer= :answerId, isAnswered= :isAnswered  WHERE questionid = :questionId")
    abstract fun updateQuestion(answerId: Long, isAnswered: Boolean, questionId: String)

    @Query("SELECT * FROM answers, questions, briefcase WHERE questions.briefCaseId = :briefcaseId AND questions.isAnswered = 1 AND answers.answerId = questions.answer")
    abstract fun getAnsweredAnswer(briefcaseId: Long): List<AnswersData>

    @Update
    abstract fun updateAnswer(answersData: AnswersData)


    //   @Insert(onConflict = OnConflictStrategy.IGNORE)
    //   fun insertBriefCase(briefCaseData: BriefCaseData)

    //   @Query("SELECT * FROM briefcase")
    //   fun getAllBriefCases(): List<BriefCaseData>

    //   @Query("SELECT * FROM briefcase WHERE briefCaseId= :id")
    //   fun getBriefCase(id: Int): BriefCaseData

//    @Query("SELECT questionId, question, answer FROM questions WHERE questionId= :id")
//    fun getBodyQuestion(id: Int): Questions
}