package com.clover.harish.repository

import androidx.lifecycle.MutableLiveData
import com.clover.harish.LocationDetailVO
import com.clover.harish.models.response.ErrorVO
import com.clover.harish.network.AppServiceClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CharacterDetailRepository {
    fun getLocationDetail(locUrl:String,
        resLiveData: MutableLiveData<LocationDetailVO>,
        errLiveData: MutableLiveData<ErrorVO>
    ) {

        val locationDetailCall: Call<LocationDetailVO> = AppServiceClient.getClient().getLocation(locUrl)

        locationDetailCall.enqueue(object : Callback<LocationDetailVO> {
            override fun onResponse(
                call: Call<LocationDetailVO>,
                response: Response<LocationDetailVO>
            ) {
                resLiveData.postValue(response.body())
            }

            override fun onFailure(call: Call<LocationDetailVO>, t: Throwable) {
                errLiveData.postValue(ErrorVO())
            }

        })
    }
}