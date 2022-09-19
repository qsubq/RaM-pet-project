package com.github.qsubq.rampetproject.app.presentation.screen.character

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.github.qsubq.rampetproject.data.model.characterModel.CharacterModel
import com.github.qsubq.rampetproject.data.repository.RemoteRepositoryImpl
import com.github.qsubq.rampetproject.domain.useCase.GetRandomCharacterUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import retrofit2.Response
import javax.inject.Inject

@HiltViewModel
class RandomCharacterViewModel @Inject constructor(
    private val useCase: GetRandomCharacterUseCase,
) : ViewModel() {
    var characterList: MutableLiveData<CharacterModel> = MutableLiveData()
    var errorLiveData : MutableLiveData<String> = MutableLiveData()

    fun getRandomCharacters() {
        viewModelScope.launch {
            when (val response = useCase.execute()) {
                is RemoteRepositoryImpl.NetworkResult.Success -> characterList.value = response.data
                is RemoteRepositoryImpl.NetworkResult.Error -> errorLiveData.value = "${response.code} ${response.message}"
                is RemoteRepositoryImpl.NetworkResult.Exception -> errorLiveData.value = "${response.e.message}"
            }
        }
    }
}
