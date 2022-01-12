package com.clover.harish.models.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.cachedIn
import com.clover.harish.app.CloverApplication
import com.clover.harish.models.response.CharacterResponseVO
import com.clover.harish.models.response.ErrorVO
import com.clover.harish.network.AppServiceClient
import com.clover.harish.repository.CharacterRepository
import com.clover.harish.repository.datasource.CharacterPagingSource
import dagger.hilt.android.scopes.ViewModelScoped

class CharacterViewModel(application: CloverApplication) : BaseViewModel(application) {
    private val characterRepository = CharacterRepository()
    val charactersLiveData: MutableLiveData<CharacterResponseVO> = MutableLiveData()
    val errorLiveData: MutableLiveData<ErrorVO> = MutableLiveData()



    fun fetchCharacters() {
        characterRepository.getCharacters(charactersLiveData,errorLiveData)
    }

    fun findCharacterByName(newText: String?) {
      newText?.let {
          characterRepository.findCharactersByName(newText,charactersLiveData,errorLiveData)
      }
    }

    val characters = Pager(PagingConfig(pageSize = 20)) {
        CharacterPagingSource(AppServiceClient.getClient())
    }.flow.cachedIn(viewModelScope)


}
