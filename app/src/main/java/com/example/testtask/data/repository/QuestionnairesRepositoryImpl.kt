package com.example.testtask.data.repository

import android.util.Log
import com.example.testtask.data.storage.network.retrofit.QuestionnairesStorageImpl
import com.example.testtask.domain.model.Questionnaires
import com.example.testtask.domain.repository.QuestionnairesRepository
import javax.inject.Inject

class QuestionnairesRepositoryImpl @Inject constructor(
    private val questionnairesStorage: QuestionnairesStorageImpl
) : QuestionnairesRepository {


    override suspend fun getQuestionnaires(): List<Questionnaires> {

        val questionnairesList = questionnairesStorage.getQuestionnaires()
        val list = mutableListOf<Questionnaires>()
        for (i in questionnairesList) {
            list.add(Mapper.questionnairesЬapDataToDomain(i))
        }

     //   Log.d("MyLog", "Questionnaries Reposytory Impl $list")
        return list
    }
}