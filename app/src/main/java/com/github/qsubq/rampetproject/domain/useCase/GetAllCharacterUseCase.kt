package com.github.qsubq.rampetproject.domain.useCase

import com.github.qsubq.rampetproject.domain.repository.RemoteRepository

class GetAllCharacterUseCase(private val repository: RemoteRepository) {
    suspend fun execute(page: Int) = repository.getAllCharacter(page)
}