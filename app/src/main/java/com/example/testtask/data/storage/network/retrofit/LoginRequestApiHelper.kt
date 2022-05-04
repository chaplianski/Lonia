package com.example.testtask.data.storage.network.retrofit

import android.content.Context
import com.example.testtask.R
import com.example.testtask.data.storage.model.LoginRequestData
import com.example.testtask.data.storage.network.service.AuthorizationApiService
import com.example.testtask.domain.exceptions.NetworkException
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.RequestBody.Companion.toRequestBody
import org.json.JSONObject
import retrofit2.Retrofit
import javax.inject.Inject

class LoginRequestApiHelper @Inject constructor() {

    @Inject
    lateinit var loginRequestretrofit: Retrofit
    @Inject
    lateinit var context: Context

    suspend fun fetchToken(loginRequestData: LoginRequestData): Int{

        val retrofit = loginRequestretrofit.create(AuthorizationApiService::class.java)
        var token = ""


        val json = JSONObject()
        json.put("email", loginRequestData.email)
        json.put("password", loginRequestData.password)
        val sendData = json.toString().toRequestBody("application/json".toMediaTypeOrNull())

        val responseToken = retrofit.fetchToken(sendData)

        var isSuccess = 0

        when (responseToken.code()) {
            in 200..299 -> {
                token = responseToken.body()?.token ?: ""
                val sharedPref = context.getSharedPreferences("Net pref", Context.MODE_PRIVATE)
                sharedPref?.edit()?.putString(NetParameters.TOKEN, responseToken.body()?.token)?.apply()
                isSuccess = 1
                return isSuccess

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

        return isSuccess
    }


}