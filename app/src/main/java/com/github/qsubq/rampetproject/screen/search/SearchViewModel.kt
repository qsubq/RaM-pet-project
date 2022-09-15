package com.github.qsubq.rampetproject.screen.search

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.github.qsubq.rampetproject.data.InternetConnection
import com.github.qsubq.rampetproject.data.repository.Repository
import com.github.qsubq.rampetproject.model.characterModel.CharacterModel
import com.github.qsubq.rampetproject.model.searchModel.SearchModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import retrofit2.Response
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
    private val repo: Repository,
    private val connectionHelper: InternetConnection,
) : ViewModel() {

    val characterLiveData: MutableLiveData<Response<SearchModel>> = MutableLiveData()
    val errorLiveData: MutableLiveData<String> = MutableLiveData()

    fun submitQuery(userSearch: String) {
        if(connectionHelper.isNetworkConnected()){
            viewModelScope.launch {
                characterLiveData.value = repo.getCharacter(userSearch)
            }
        }else{
            errorLiveData.value = "Check the Internet connection"
        }
    }
}