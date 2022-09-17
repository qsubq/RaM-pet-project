package com.github.qsubq.rampetproject.domain.useCase

import android.widget.SearchView
import com.github.qsubq.rampetproject.data.model.searchModel.SearchModel
import com.github.qsubq.rampetproject.domain.repository.RemoteRepository
import retrofit2.Response

class GetSearchCharacterUseCase(private val repo: RemoteRepository) {
    suspend fun execute(nameCharacter: String):Response<SearchModel> {
        return repo.getCharacterFromSearch(nameCharacter)
    }
}