package com.github.qsubq.rampetproject.app.presentation.screen.search

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.github.qsubq.rampetproject.data.model.searchModel.SearchModel
import com.github.qsubq.rampetproject.domain.useCase.GetSearchCharacterUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import retrofit2.Response
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
    private val useCase: GetSearchCharacterUseCase,
) : ViewModel() {

    val characterLiveData: MutableLiveData<Response<SearchModel>> = MutableLiveData()
    val errorLiveData: MutableLiveData<String> = MutableLiveData()

    fun submitQuery(userSearch: String) {

        viewModelScope.launch {
            characterLiveData.value = useCase.execute(userSearch)

        }
    }
}