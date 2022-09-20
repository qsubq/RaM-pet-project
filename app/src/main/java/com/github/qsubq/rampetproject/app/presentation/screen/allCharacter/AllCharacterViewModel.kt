package com.github.qsubq.rampetproject.app.presentation.screen.allCharacter

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.cachedIn
import com.github.qsubq.rampetproject.data.paging.CharacterPagingSource
import com.github.qsubq.rampetproject.domain.useCase.GetAllCharacterUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class AllCharacterViewModel @Inject constructor(useCase: GetAllCharacterUseCase) : ViewModel() {

    val listData = Pager(PagingConfig(pageSize = 1)) {
        CharacterPagingSource(useCase)
    }.flow.cachedIn(viewModelScope)
}