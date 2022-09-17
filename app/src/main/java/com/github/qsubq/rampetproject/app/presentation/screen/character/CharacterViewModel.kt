package com.github.qsubq.rampetproject.app.presentation.screen.character

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.github.qsubq.rampetproject.data.model.characterModel.CharacterModel
import com.github.qsubq.rampetproject.domain.useCase.GetRandomCharacterUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import retrofit2.Response
import javax.inject.Inject

@HiltViewModel
class CharacterViewModel @Inject constructor(
    private val useCase: GetRandomCharacterUseCase,
) : ViewModel() {


    var characterList: MutableLiveData<Response<CharacterModel>> = MutableLiveData()

    fun getRandomCharacters() {
        viewModelScope.launch {
            characterList.value = useCase.execute()
        }
    }
}
