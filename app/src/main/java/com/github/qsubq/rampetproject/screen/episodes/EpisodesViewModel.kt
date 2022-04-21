package com.github.qsubq.rampetproject.screen.episodes

import android.app.Application
import androidx.lifecycle.*
import com.github.qsubq.rampetproject.data.ConnectionHelper
import com.github.qsubq.rampetproject.data.repository.Repository
import com.github.qsubq.rampetproject.model.episodeModel.EpisodesModel
import kotlinx.coroutines.launch
import retrofit2.Response

class EpisodesViewModel(application: Application) : AndroidViewModel(application) {
    val context = application
    private val repo = Repository()
    var episodesList : MutableLiveData<Response<EpisodesModel>> = MutableLiveData()

    fun getAllEpisodes(){
        viewModelScope.launch {
            episodesList.value = repo.getAllEpisodes()
        }
    }

    fun isOnline() : Boolean {
        return ConnectionHelper.isOnline(context)
    }
}