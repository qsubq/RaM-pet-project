package com.github.qsubq.rampetproject.domain.useCase

import com.github.qsubq.rampetproject.domain.repository.RemoteRepository

class GetRandomCharacterUseCase(private val repo: RemoteRepository) {
    suspend fun execute() = repo.getRandomCharacter()
}