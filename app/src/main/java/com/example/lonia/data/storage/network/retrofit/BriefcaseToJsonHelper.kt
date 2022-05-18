package com.example.lonia.data.storage.network.retrofit

import android.content.Context
import android.util.Log
import com.example.lonia.R
import com.example.lonia.data.storage.dao.BriefcaseDao
import com.example.lonia.data.storage.database.PhotoConverters
import com.example.lonia.data.storage.network.service.BriefcaseSendApiService
import com.example.lonia.domain.exceptions.NetworkException
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.RequestBody
import okhttp3.RequestBody.Companion.toRequestBody
import retrofit2.Retrofit
import java.text.SimpleDateFormat
import java.util.*
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
        val sendData = getJson(briefcaseId)
        val responseBriefcase = retrofit.saveBriefcase("Bearer $token", sendData)

        when (responseBriefcase.code()) {
            in 200..299 -> {
                saveResult = responseBriefcase.body()?.status ?: ""
            }
            in 300..399 -> {
                throw NetworkException(R.string.internet_error)
            }
            in 400..499 -> {
                throw NetworkException(R.string.client_error)
            }
            in 500..599 -> {
                throw NetworkException(R.string.server_error)
            }
        }
        return saveResult
    }

    fun getJson(briefcaseId: Long): RequestBody{

        val briefcaseJson = StringBuilder()
        val briefcase = briefcaseDao.getBriefCase(briefcaseId)

        val formatedate = SimpleDateFormat("yyyy-MM-dd HH:mm:ss Z", Locale.getDefault())
        val dateBriefcase = formatedate.format(briefcase.dateOfCreation)

        briefcaseJson.append("{\"briefcase\" : {\n" +

                "\"vessel\" : \"${briefcase.vessel}\", \n" +
                "\"InspectionSource\" : \"${briefcase.inspector}\", \n"+
                "\"name_case\": \"${briefcase.inspectorName}_${briefcase.category}_${briefcase.dateOfCreation}\", \n" +
                "\"date_in_vessel\" : \"$dateBriefcase\", \n" +
                "\"port\" : \"${briefcase.port}\", \n" +
                "\"InspectorName\" : \"${briefcase.inspectorName}\", \n" +
                "\"InspectionTypes\" : \"${briefcase.inspectorType}\" \n" +
                "}, \n" +
                "\"answer\" : {"
           )

        val questionsList = briefcaseDao.getAllQuestions(briefcaseId)
        var answerCount = 1

        for (question in questionsList){
            Log.d("MyLog", "${question.question}")
        }

        for (question in questionsList){
            var idCategory = question.categoryid
            if (idCategory.equals("")){
                idCategory = "1929"
            }

            val rightQuestion = getStringWithoutBreak(question.question)
            val rightComment = getStringWithoutBreak(question.commentForQuestion)

            briefcaseJson.append(
                      "\"answer_${answerCount++}\" : { \n" +
                      "\"comment\" : \"${rightComment}\", \n" +
                      "\"question\" : \"${rightQuestion}\", \n" +
                      "\"origin\" : \"${question.origin}\", \n" +
                      "\"questionid\" : \"${question.questionid}\", \n" +
                      "\"categoryid\" : ${idCategory}, \n" +
                       "\"categorynewid\" : \"${question.categorynewid}\", \n" +
                      "\"answer\" : ${question.answer}, \n" +
                      "\"questioncode\" : \"${question.questioncode}\" \n"

//                      "\"dateofinspection\" : ${question.dateOfInspection}, " +
//                      "\"isanswered\" : ${question.isAnswered}, " +
//                      "\"commentforquestion\" : ${question.commentForQuestion}, " +
//                      "\"significance\" : ${question.significance}, "
           )

           val photoList = briefcaseDao.getPhotos(question.questionid)
           var photoCount = 1
            val converter = PhotoConverters()
           if (!photoList.isEmpty()){
               briefcaseJson.append(", \"data_image\": {")
               for (photo in photoList){
                   val rightPhotoString = getStringWithoutBreak(converter.fromBitmap(photo.photoUri))

                   briefcaseJson.append("\"foto_${photoCount++}\": \"${rightPhotoString}\"")
                   if (photoList.size >= photoCount) briefcaseJson.append(",")
               }
               briefcaseJson.append("}")
               briefcaseJson.append("}")
           }
            briefcaseJson.append("}")
            if (questionsList.size >= answerCount) briefcaseJson.append(",")
        }
        briefcaseJson.append("}}")

        val jsonNew = briefcaseJson.toString()
        val sendData = jsonNew.toRequestBody("application/json".toMediaTypeOrNull())

        return sendData
    }

    private fun getStringWithoutBreak(beginStringData: String): String {

        var stringData = beginStringData.replace("\n", "\\n")
        stringData = stringData.replace("\"", "\\\"")
//        stringData = stringData.replace("/", "")
        stringData = stringData.replace("\r", " ")
        stringData = stringData.replace("\r\n", " ")
        stringData = stringData.replace("\b", "\\b")
        stringData = stringData.replace("\t", "\\t")
        stringData = stringData.replace("/+", "\\/+")

        return stringData
    }


}