package com.github.qsubq.rampetproject.app.presentation.screen.episodes

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.cachedIn
import com.github.qsubq.rampetproject.data.paging.EpisodesPagingSource
import com.github.qsubq.rampetproject.domain.useCase.GetAllEpisodesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class EpisodesViewModel @Inject constructor(
    private val useCase: GetAllEpisodesUseCase,
) : ViewModel() {

    val listData = Pager(PagingConfig(pageSize = 1)) {
        EpisodesPagingSource(useCase)
    }.flow.cachedIn(viewModelScope)

}