package com.example.testtask.data.repository

import android.util.Log
import com.example.testtask.data.storage.database.QuestionsStorageImpl
import com.example.testtask.data.storage.network.retrofit.NetQuestionsRepository
import com.example.testtask.domain.model.Answers
import com.example.testtask.domain.model.Questions
import com.example.testtask.domain.repository.QuestionsRepository
import javax.inject.Inject

class QuestionsRepositoryImpl @Inject constructor(
    private val questionsStorage: QuestionsStorageImpl,
    private val netQuestionsRepository: NetQuestionsRepository
) : QuestionsRepository {

    override fun getQuestions(briefcaseId: Long): List<Questions> {
        val questionsList = questionsStorage.getQuestionsList(briefcaseId)
        Log.d("My log", "QuestionsRepository  questionslist = $questionsList")

        val list = mutableListOf<Questions>()
        for (i in questionsList) {
            list.add(Mapper.questionsMapDataToDomain(i))
        }
        Log.d("My log", "QuestionsRepository list = $list")
        return list
    }

    override suspend fun fetchQuestions(qid: Int): List<Questions> {
        val questionsLDataList = netQuestionsRepository.getQuestions(qid)
        val questionsList = mutableListOf<Questions>()
        for (i in questionsLDataList) {
            questionsList.add(Mapper.questionsMapDataToDomain(i))
        }
        return questionsList.toList()
    }

    override fun updateQuestions(questions: Questions, answers: Answers) {
       val questionsData = Mapper.questionsMapDomainToData(questions)
       val answersData = Mapper.answersMapDomainToData(answers)
        questionsStorage.updateQuestions(questionsData,answersData)

        Log.d("My Log", "questionId in Dao: ${questions.questionid}")
    }

    //  override fun addQuestions(question: Questions) {
    //      val questionsData = Mapper.questionsMapDomainToData(question)
    //      questionsStorage.addAllQuestions(questionsData)
    //   }
}