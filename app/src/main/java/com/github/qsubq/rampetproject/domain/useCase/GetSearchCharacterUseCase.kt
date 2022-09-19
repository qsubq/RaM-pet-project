package com.github.qsubq.rampetproject.domain.useCase

import com.github.qsubq.rampetproject.data.model.searchModel.SearchModel
import com.github.qsubq.rampetproject.data.repository.RemoteRepositoryImpl.NetworkResult
import com.github.qsubq.rampetproject.domain.repository.RemoteRepository

class GetSearchCharacterUseCase(private val repo: RemoteRepository) {
    suspend fun execute(nameCharacter: String) = repo.getCharacterFromSearch(nameCharacter)

}