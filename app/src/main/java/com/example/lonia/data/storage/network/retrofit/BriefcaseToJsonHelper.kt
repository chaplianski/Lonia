package com.example.lonia.data.storage.network.retrofit

import android.content.Context
import android.util.Log
import com.example.lonia.R
import com.example.lonia.data.storage.dao.BriefcaseDao
import com.example.lonia.data.storage.model.BriefCaseData
import com.example.lonia.data.storage.model.PhotosData
import com.example.lonia.data.storage.model.QuestionsData
import com.example.lonia.data.storage.network.service.BriefcaseSendApiService
import com.example.lonia.data.storage.network.service.QuestionsApiService
import com.example.lonia.domain.exceptions.NetworkException
import com.squareup.moshi.Json
import com.squareup.moshi.Moshi
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.RequestBody
import okhttp3.RequestBody.Companion.toRequestBody
import org.json.JSONObject
import retrofit2.Retrofit
import javax.inject.Inject

class BriefcaseToJsonHelper @Inject constructor() {

    @Inject
    lateinit var briefcaseDao: BriefcaseDao
    @Inject
    lateinit var briefcaseRetrofit: Retrofit
    @Inject
    lateinit var context: Context


    suspend fun getSendResult(briefcaseId: Long): String{

        val retrofit = briefcaseRetrofit.create(BriefcaseSendApiService::class.java)
        var saveResult = ""

        val sharedPref = context.getSharedPreferences("Net pref", Context.MODE_PRIVATE)
        val token = sharedPref?.getString(NetParameters.TOKEN, "")
        Log.d("MyLog", "briefcase helper sendData: 1")
        val sendData = getJson(briefcaseId)
        Log.d("MyLog", "briefcase helper sendData: 2")
        Log.d("MyLog", "briefcase helper sendData: ${sendData}")
        val responseBriefcase = retrofit.saveBriefcase("Bearer $token", sendData)

        when (responseBriefcase.code()) {
            in 200..299 -> {
                Log.d("MyLog", "briefcase helper response code: ${responseBriefcase.code()}")
                saveResult = responseBriefcase.body() ?: ""
            }
            in 300..399 -> {

                Log.d("MyLog", "briefcase helper response code: ${responseBriefcase.code()}")
                throw NetworkException(R.string.internet_error)
            }
            in 400..499 -> {
                Log.d("MyLog", "briefcase helper response code: ${responseBriefcase.code()}")
                throw NetworkException(R.string.client_error)
            }
            in 500..599 -> {
                Log.d("MyLog", "briefcase helper response code: ${responseBriefcase.code()}")
                throw NetworkException(R.string.server_error)
            }
        }
        return saveResult
    }

    fun getJson(briefcaseId: Long): RequestBody{

        val briefcaseJson = StringBuilder()
        val briefcase = briefcaseDao.getBriefCase(briefcaseId)

        briefcaseJson.append("\"briefcase\":{" +
                "\"name_case\": ${briefcase.inspectorName}_${briefcase.category}_${briefcase.dateOfCreation},"+
                "\"InspectorName\": ${briefcase.inspectorName}, " +
                "\"InspectionTypes\": ${briefcase.inspectorType}, " +
                "\"InspectionSource\": ${briefcase.inspector}, "+
                "\"vessel\": ${briefcase.vessel}, " +
                "\"port\": ${briefcase.port}, " +
                "\"date_in_vessel\": ${briefcase.dateOfCreation}}," +
                "\"answer\" : ["
           )

        val questionsList = briefcaseDao.getAllQuestions(briefcaseId)
        var answerCount = 0

        for (question in questionsList){
            briefcaseJson.append(
              "{\"answer_${answerCount++}\": {" +
                      "\"answer\": ${question.answer}," +
                      "\"comment\": ${question.comment}, " +
                      "\"questionid\": ${question.questionid}, " +
                      "\"question\": ${question.question}, " +
                      "\"questioncode\": ${question.questioncode}," +
                      "\"categoryid\": ${question.categoryid}," +
                      "\"categorynewid\": ${question.categorynewid}, " +
                      "\"origin\": ${question.origin}"

//                      "\"dateofinspection\" : ${question.dateOfInspection}, " +
//                      "\"isanswered\" : ${question.isAnswered}, " +
//                      "\"commentforquestion\" : ${question.commentForQuestion}, " +
//                      "\"significance\" : ${question.significance}, "
           )
 //           Log.d("MyLog", "briefcase json helper answerCount: $answerCount, ${question.questioncode}")

           val photoList = briefcaseDao.getPhotos(question.questionid)
           var photoCount = 0
           if (!photoList.isEmpty()){
               briefcaseJson.append(", \"data_image\": [")
               for (photo in photoList){
                   briefcaseJson.append("\"foto_${photoCount++}\": ${photo.photoUri},")
               }
               briefcaseJson.append("]")
               Log.d("MyLog", "briefcase helper sendData: 1-1")
           }
            Log.d("MyLog", "briefcase helper sendData: 1-2")
            briefcaseJson.append("}")
        }
        Log.d("MyLog", "briefcase helper sendData: 1-3")
        briefcaseJson.append("]")
        val jsonNew = briefcaseJson.toString()
        Log.d("MyLog", "briefcase helper sendData json: $jsonNew")
 //       Log.d("MyLog", "briefcase json helper: $briefcaseJson")
        Log.d("MyLog", "briefcase helper sendData: 1-4")
 //       val json = JSONObject(jsonNew)
        Log.d("MyLog", "briefcase helper sendData: 1-5")
        val sendData = jsonNew.toRequestBody("application/json".toMediaTypeOrNull())
//        val sendData = json.toString().toRequestBody("application/json".toMediaTypeOrNull())
        Log.d("MyLog", "briefcase helper sendData: 1-6")
        return sendData
    }


}