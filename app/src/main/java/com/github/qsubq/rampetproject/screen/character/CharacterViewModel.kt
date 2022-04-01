package com.github.qsubq.rampetproject.screen.character

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.github.qsubq.rampetproject.data.repository.Repository
import com.github.qsubq.rampetproject.model.CharacterModel
import com.github.qsubq.rampetproject.model.CharacterModelItem
import kotlinx.coroutines.launch
import retrofit2.Response

class CharacterViewModel : ViewModel() {
    private val repo = Repository()
    var characterList : MutableLiveData<Response<CharacterModel>> = MutableLiveData()

    fun getAllCharacters(){
        viewModelScope.launch {
            characterList.value = repo.getCharacters()
        }
    }
}