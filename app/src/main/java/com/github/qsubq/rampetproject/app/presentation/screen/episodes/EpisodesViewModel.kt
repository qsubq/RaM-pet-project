package com.github.qsubq.rampetproject.app.presentation.screen.episodes

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.github.qsubq.rampetproject.data.InternetConnection
import com.github.qsubq.rampetproject.data.repository.Repository
import com.github.qsubq.rampetproject.data.model.episodeModel.EpisodesModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import retrofit2.Response
import javax.inject.Inject

@HiltViewModel
class EpisodesViewModel @Inject constructor(
    private val repository: Repository,
    private val connectionHelper: InternetConnection
) : ViewModel() {
    var episodesList: MutableLiveData<Response<EpisodesModel>> = MutableLiveData()

    fun getAllEpisodes() {
        viewModelScope.launch {
            episodesList.value = repository.getAllEpisodes()
        }
    }

    fun isOnline(): Boolean {
        return connectionHelper.isNetworkConnected()
    }
}