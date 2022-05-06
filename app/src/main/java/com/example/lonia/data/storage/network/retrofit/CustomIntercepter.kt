package com.example.lonia.data.storage.network.retrofit

import okhttp3.Interceptor
import okhttp3.Response

class CustomIntercepter : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {

        val request = chain.request()
        val response = chain.proceed(request)
        if (response.code == 200){

        }
        return response
    }
}