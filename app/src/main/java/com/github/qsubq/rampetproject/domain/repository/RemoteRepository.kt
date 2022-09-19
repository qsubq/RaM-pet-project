package com.github.qsubq.rampetproject.domain.repository

import com.github.qsubq.rampetproject.data.model.characterModel.CharacterModel
import com.github.qsubq.rampetproject.data.model.episodeModel.EpisodesModel
import com.github.qsubq.rampetproject.data.model.searchModel.SearchModel
import com.github.qsubq.rampetproject.data.repository.RemoteRepositoryImpl
import com.github.qsubq.rampetproject.data.repository.RemoteRepositoryImpl.*
import retrofit2.Response

interface RemoteRepository {
    suspend fun getRandomCharacter(): Response<CharacterModel>
    suspend fun getAllEpisodes(page: Int): Response<EpisodesModel>
    suspend fun getCharacterFromSearch(nameCharacter: String): NetworkResult<SearchModel>
}