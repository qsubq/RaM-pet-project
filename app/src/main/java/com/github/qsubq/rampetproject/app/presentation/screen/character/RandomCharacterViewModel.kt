package com.github.qsubq.rampetproject.app.presentation.screen.character

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.github.qsubq.rampetproject.data.model.characterModel.CharacterModel
import com.github.qsubq.rampetproject.data.repository.RemoteRepositoryImpl.*
import com.github.qsubq.rampetproject.domain.useCase.GetRandomCharacterUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RandomCharacterViewModel @Inject constructor(
    private val useCase: GetRandomCharacterUseCase,
) : ViewModel() {
    private val _myUiState = MutableStateFlow<NetworkResult<CharacterModel>>(NetworkResult.Success(
        CharacterModel()))
    val myUiState: StateFlow<NetworkResult<CharacterModel>> = _myUiState
    var isAlreadyGetCharacters: Boolean = false

    fun getRandomCharacters() {
        viewModelScope.launch {
            _myUiState.value = useCase.execute()
        }
        isAlreadyGetCharacters = true
    }
}
