package com.clover.harish.models

import com.google.gson.annotations.SerializedName

data class CharacterVO (
	@SerializedName("id") val id : Int,
	@SerializedName("name") val name : String,
	@SerializedName("status") val status : String,
	@SerializedName("species") val species : String,
	@SerializedName("type") val type : String,
	@SerializedName("gender") val gender : String,
	@SerializedName("origin") val originVO : OriginVO,
	@SerializedName("location") val location : LocationVO,
	@SerializedName("image") val image : String,
	@SerializedName("episode") val episode : List<String>,
	@SerializedName("url") val url : String,
	@SerializedName("created") val created : String
)