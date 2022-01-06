package com.clover.harish.models.viewmodels

import androidx.lifecycle.MutableLiveData
import com.clover.harish.LocationDetailVO
import com.clover.harish.app.CloverApplication
import com.clover.harish.models.response.ErrorVO
import com.clover.harish.repository.CharacterDetailRepository

class CharacterDetailViewModel(application: CloverApplication) : BaseViewModel(application) {
    private val characterDetailRepository = CharacterDetailRepository()
    val locationDetailLiveData: MutableLiveData<LocationDetailVO> = MutableLiveData()
    val errorLiveData: MutableLiveData<ErrorVO> = MutableLiveData()


    fun fetchLocationDetail(locationUrl: String) {
        characterDetailRepository.getLocationDetail(
            locationUrl,
            locationDetailLiveData,
            errorLiveData
        )
    }


}
