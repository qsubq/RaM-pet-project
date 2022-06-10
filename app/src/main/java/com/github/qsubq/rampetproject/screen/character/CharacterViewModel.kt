package com.github.qsubq.rampetproject.screen.character

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.github.qsubq.rampetproject.data.InternetConnection
import com.github.qsubq.rampetproject.data.repository.Repository
import com.github.qsubq.rampetproject.model.characterModel.CharacterModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import retrofit2.Response
import javax.inject.Inject

@HiltViewModel
class CharacterViewModel @Inject constructor(
    private val repository: Repository,
    private val connectionHelper: InternetConnection
) : ViewModel() {


    var characterList: MutableLiveData<Response<CharacterModel>> = MutableLiveData()

    fun getRandomCharacters() {
        viewModelScope.launch {
            characterList.value = repository.getRandomCharacters()
        }
    }

    fun isOnline(): Boolean {
        return connectionHelper.isNetworkConnected()
    }
}
