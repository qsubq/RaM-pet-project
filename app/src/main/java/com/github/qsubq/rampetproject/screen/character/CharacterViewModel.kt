package com.github.qsubq.rampetproject.screen.character

import android.app.Application
import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.github.qsubq.rampetproject.data.ConnectionHelper
import com.github.qsubq.rampetproject.data.repository.Repository
import com.github.qsubq.rampetproject.model.characterModel.CharacterModel
import kotlinx.coroutines.launch
import retrofit2.Response

class CharacterViewModel(application: Application) : AndroidViewModel(application) {
    val context = application
    private val repo = Repository()
    var characterList: MutableLiveData<Response<CharacterModel>> = MutableLiveData()

    fun getRandomCharacters() {
        viewModelScope.launch {
            characterList.value = repo.getRandomCharacters()
        }
    }

    fun isOnline() : Boolean {
        return ConnectionHelper.isOnline(context)
    }
}
