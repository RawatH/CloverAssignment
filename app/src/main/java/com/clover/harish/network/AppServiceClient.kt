package com.clover.harish.network

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object  AppServiceClient {
    val BASE_URL = "https://rickandmortyapi.com/api/"

    fun getClient():APIServices{

        val logIntercepter = HttpLoggingInterceptor()
        logIntercepter.setLevel(HttpLoggingInterceptor.Level.BODY)

        val client = OkHttpClient.Builder()
            .addInterceptor(logIntercepter)
            .build()

        return Retrofit.Builder()
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(BASE_URL)
            .build()
            .create(APIServices::class.java)
    }

}