package com.clover.harish.models.viewmodels

import androidx.lifecycle.MutableLiveData
import com.clover.harish.app.CloverApplication
import com.clover.harish.models.response.CharacterResponseVO
import com.clover.harish.models.response.ErrorVO
import com.clover.harish.repository.CharacterRepository

class CharacterViewModel(application: CloverApplication) : BaseViewModel(application) {
    private val characterRepository = CharacterRepository()
    val charactersLiveData: MutableLiveData<CharacterResponseVO> = MutableLiveData()
    val errorLiveData: MutableLiveData<ErrorVO> = MutableLiveData()


    fun fetchCharacters() {
        characterRepository.getCharacters(charactersLiveData,errorLiveData)
    }


}
