package com.clover.harish.models

import com.google.gson.annotations.SerializedName

data class OriginVO (
	@SerializedName("name") val name : String,
	@SerializedName("url") val url : String
)