package com.github.qsubq.rampetproject.data.repository

import com.github.qsubq.rampetproject.data.api.ApiService
import com.github.qsubq.rampetproject.data.model.characterModel.CharacterModel
import com.github.qsubq.rampetproject.data.model.episodeModel.EpisodesModel
import com.github.qsubq.rampetproject.data.model.searchModel.SearchModel
import com.github.qsubq.rampetproject.domain.repository.RemoteRepository
import retrofit2.Response

class RemoteRepositoryImpl(private val api: ApiService) : RemoteRepository {

    override suspend fun getRandomCharacter(): Response<CharacterModel> {
        val randomList = mutableListOf<Int>()
        for (i in 0..20) {
            randomList.add((1..826).random())
        }
        return api.getRandomCharacters(randomList.toString())
    }

    override suspend fun getAllEpisodes(page: Int): Response<EpisodesModel> {
        return api.getAllEpisode(page)
    }

    override suspend fun getCharacterFromSearch(nameCharacter: String): Response<SearchModel> {
        return api.getCharacter(nameCharacter)
    }
}