package com.example.testtask.data.storage.network.retrofit

import android.content.Context
import android.util.Log
import com.example.testtask.data.storage.model.LoginRequestData
import com.example.testtask.data.storage.model.LoginResponseData
import com.example.testtask.data.storage.network.service.AuthorizationApiService
import com.example.testtask.presenter.ui.Constants
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

    suspend fun fetchToken(loginRequestData: LoginRequestData): LoginResponseData{

        val retrofit = loginRequestretrofit.create(AuthorizationApiService::class.java)
        Log.d("My Log", "Login Helper - email: ${loginRequestData.email}, password: ${loginRequestData.password}")

        val json = JSONObject()
        json.put("email", loginRequestData.email)
        json.put("password", loginRequestData.password)
        val sendData = json.toString().toRequestBody("application/json".toMediaTypeOrNull())

        val responseToken = retrofit.fetchToken(sendData)

        val sharedPref = context.getSharedPreferences("Net pref", Context.MODE_PRIVATE)
        sharedPref?.edit()?.putString(NetParameters.TOKEN1, responseToken.body()?.token)?.apply()

        Log.d("My Log", "Token: ${responseToken.body()}")
        return responseToken.body()!!
    }


}