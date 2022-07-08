package com.example.lonia.data.storage.network.retrofit


import com.example.lonia.data.storage.network.service.QuestionsApiService
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.RequestBody.Companion.toRequestBody
import org.json.JSONObject
import retrofit2.Retrofit
import java.io.IOException
import java.net.ConnectException
import java.net.UnknownHostException
import javax.inject.Inject

//class NetQuestionsRepository @Inject constructor() {
//
//    @Inject
//    lateinit var questionnairesRetrofit: Retrofit
//
//    suspend fun getQuestions(qidcode: Int): QuestionsDataResponse {
//        val retrofit = questionnairesRetrofit.create(QuestionsApiService::class.java)
//        var questionsDataResponse = QuestionsDataResponse()
//
//        val json = JSONObject()
//        json.put("qid", qidcode)
//        val sendData = json.toString().toRequestBody("application/json".toMediaTypeOrNull())
//
//        try {
//            val responseQuestions =
//                retrofit.fetchQuestions("Bearer ${com.example.lonia.data.storage.network.retrofit.NetParameters.TOKEN}", sendData)
//            when (responseQuestions.code()) {
//                in 200..299 -> {
//
//      //              val questionnairesList =
//      //                  responseQuestions.body()?.map { it.questionsMapDataToDomain() }
//
//                    val questionnairesList = responseQuestions.body()
//
//                    questionsDataResponse =
//                        questionnairesList.let {
//                            QuestionsDataResponse(
//                                response = it,
//                                status = "Success"
//                            )
//                        }
//                }
//                in 300..399 -> {
//                    questionsDataResponse =
//                        QuestionsDataResponse(status = "Redirects. Code: ${responseQuestions.code()}")
//                }
//                in 400..499 -> {
//                    questionsDataResponse =
//                        QuestionsDataResponse(status = "Client Error. Code: ${responseQuestions.code()}")
//                }
//                in 500..599 -> {
//                    questionsDataResponse =
//                        QuestionsDataResponse(status = "Server Error. Code: ${responseQuestions.code()}")
//                }
//            }
//        } catch (e: IOException) {
//            questionsDataResponse = QuestionsDataResponse(status = "Check your Internet connection")
//        } catch (e: UnknownHostException) {
//            questionsDataResponse = QuestionsDataResponse(status = "Check your Internet connection")
//        } catch (e: ConnectException) {
//            questionsDataResponse = QuestionsDataResponse(status = "Check your Internet connection")
//        } catch (e: Exception) {
//            questionsDataResponse =
//                QuestionsDataResponse(status = "Unknown Error. Restart the app.")
//        }
//
//        return questionsDataResponse
//    }
//}

