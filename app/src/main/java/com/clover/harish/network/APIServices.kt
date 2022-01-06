package com.clover.harish.network

import com.clover.harish.models.response.CharacterResponseVO
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Headers


interface APIServices {


    @Headers("ContentType: application/json")
    @GET("character")
    fun getCharacters(): Call<CharacterResponseVO>



}