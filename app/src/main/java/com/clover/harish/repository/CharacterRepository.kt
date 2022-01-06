package com.clover.harish.repository

import androidx.lifecycle.MutableLiveData
import com.clover.harish.models.response.CharacterResponseVO
import com.clover.harish.models.response.ErrorVO
import com.clover.harish.network.AppServiceClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CharacterRepository {

    fun getCharacters(
        resLiveData: MutableLiveData<CharacterResponseVO>,
        errLiveData: MutableLiveData<ErrorVO>
    ) {
        val characterCall: Call<CharacterResponseVO> = AppServiceClient.getClient().getCharacters()

        characterCall.enqueue(object : Callback<CharacterResponseVO> {
            override fun onResponse(
                call: Call<CharacterResponseVO>,
                response: Response<CharacterResponseVO>
            ) {
                resLiveData.postValue(response.body())
            }

            override fun onFailure(call: Call<CharacterResponseVO>, t: Throwable) {
                errLiveData.postValue(ErrorVO())
            }

        })
    }
}