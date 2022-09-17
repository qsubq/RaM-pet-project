package com.github.qsubq.rampetproject.app.presentation.screen.episodes

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.cachedIn
import com.github.qsubq.rampetproject.data.InternetConnection
import com.github.qsubq.rampetproject.data.api.ApiService
import com.github.qsubq.rampetproject.data.paging.EpisodesPagingSource
import com.github.qsubq.rampetproject.data.repository.Repository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class EpisodesViewModel @Inject constructor(
    private val apiService: ApiService,
    private val connectionHelper: InternetConnection,
) : ViewModel() {

    val listData = Pager(PagingConfig(pageSize = 1)) {
        EpisodesPagingSource(apiService)
    }.flow.cachedIn(viewModelScope)


    fun isOnline(): Boolean {
        return connectionHelper.isNetworkConnected()
    }
}