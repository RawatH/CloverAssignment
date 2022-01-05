package com.clover.harish.network

import com.clover.harish.utli.AppConst
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object  AppServiceClient {

    fun getClient():APIServices{

        var logIntercepter = HttpLoggingInterceptor()
        logIntercepter.setLevel(HttpLoggingInterceptor.Level.BODY)

        var client = OkHttpClient.Builder()
            .addInterceptor(logIntercepter)
            .build()

        return Retrofit.Builder()
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(AppConst.BASE_URL)
            .build()
            .create(APIServices::class.java)
    }

}