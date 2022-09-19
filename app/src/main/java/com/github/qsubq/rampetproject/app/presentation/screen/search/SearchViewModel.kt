package com.github.qsubq.rampetproject.app.presentation.screen.search

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.github.qsubq.rampetproject.data.model.characterModel.CharacterModelItem
import com.github.qsubq.rampetproject.data.repository.RemoteRepositoryImpl
import com.github.qsubq.rampetproject.domain.useCase.GetSearchCharacterUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
    private val useCase: GetSearchCharacterUseCase,
) : ViewModel() {

    val characterLiveData: MutableLiveData<List<CharacterModelItem>> = MutableLiveData()
    val errorLiveData: MutableLiveData<String> = MutableLiveData()

    fun submitQuery(userSearch: String) {
        viewModelScope.launch {
            when (val response = useCase.execute(userSearch)) {
                is RemoteRepositoryImpl.NetworkResult.Success -> characterLiveData.value = response.data.results
                is RemoteRepositoryImpl.NetworkResult.Error -> errorLiveData.value = "${response.code} ${response.message}"
                is RemoteRepositoryImpl.NetworkResult.Exception -> errorLiveData.value = "${response.e.message}"
            }
        }
    }
}