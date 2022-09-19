package com.github.qsubq.rampetproject.domain.useCase

import com.github.qsubq.rampetproject.domain.repository.RemoteRepository

class GetSearchCharacterUseCase(private val repo: RemoteRepository) {
    suspend fun execute(nameCharacter: String) = repo.getCharacterFromSearch(nameCharacter)

}