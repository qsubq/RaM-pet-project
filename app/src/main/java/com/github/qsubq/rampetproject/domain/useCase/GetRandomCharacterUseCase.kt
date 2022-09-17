package com.github.qsubq.rampetproject.domain.useCase

import com.github.qsubq.rampetproject.data.model.characterModel.CharacterModel
import com.github.qsubq.rampetproject.domain.repository.RemoteRepository
import retrofit2.Response

class GetRandomCharacterUseCase(private val repo: RemoteRepository) {
    suspend fun execute() : Response<CharacterModel>{
        return repo.getRandomCharacter()
    }
}