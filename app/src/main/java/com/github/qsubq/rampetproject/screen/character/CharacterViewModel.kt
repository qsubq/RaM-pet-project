package com.github.qsubq.rampetproject.screen.character

import android.app.Application
import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.github.qsubq.rampetproject.R
import com.github.qsubq.rampetproject.data.repository.Repository
import com.github.qsubq.rampetproject.model.CharacterModel
import com.google.android.material.snackbar.Snackbar
import kotlinx.coroutines.launch
import retrofit2.Response

class CharacterViewModel(application: Application) : AndroidViewModel(application) {
    private val repo = Repository()
    var characterList : MutableLiveData<Response<CharacterModel>> = MutableLiveData()
    private val context = application


    fun getAllCharacters() : Boolean{
        return if(isOnline(context)){
            viewModelScope.launch {
                characterList.value = repo.getCharacters()
            }
            true
        } else{
            false
        }
    }


    private fun isOnline(context: Context): Boolean {
        val connectivityManager =
            context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val capabilities =
            connectivityManager.getNetworkCapabilities(connectivityManager.activeNetwork)
        if (capabilities != null) {
            if (capabilities.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET)
                || capabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI)) {
                Log.i("Internet", "NetworkCapabilities.TRANSPORT_CELLULAR")
                return true
            } else {
                Log.i("Internet", "No network connection")
            }
        }
        return false
    }
}