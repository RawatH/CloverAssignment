package com.clover.harish.network

import com.clover.harish.LocationDetailVO
import com.clover.harish.models.response.CharacterResponseVO
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query
import retrofit2.http.Url





interface APIServices {


    /**
     * Fetch all characters
     */
    @Headers("ContentType: application/json")
    @GET("character")
    fun getCharacters(): Call<CharacterResponseVO>

    /**
     * Fetch all characters
     */
    @Headers("ContentType: application/json")
    @GET("character")
    fun getCharactersByName(@Query("name")name:String): Call<CharacterResponseVO>


    /**
     * Get location details
     */
    @Headers("ContentType: application/json")
    @GET
    fun getLocation(@Url url: String?): Call<LocationDetailVO>

    /**
     * Fetch all characters
     */
    @Headers("ContentType: application/json")
    @GET("character/")
    suspend fun getCharacterByPage(@Query("page")page:Int): CharacterResponseVO


}