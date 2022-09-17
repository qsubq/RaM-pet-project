package com.github.qsubq.rampetproject.data.repository

import com.github.qsubq.rampetproject.data.api.ApiService
import com.github.qsubq.rampetproject.data.model.characterModel.CharacterModel
import com.github.qsubq.rampetproject.data.model.episodeModel.EpisodesModel
import com.github.qsubq.rampetproject.data.model.searchModel.SearchModel
import retrofit2.Response

class Repository(private val api: ApiService) {

    suspend fun getRandomCharacters(): Response<CharacterModel> {
        val randomList = mutableListOf<Int>()
        for (i in 0..20) {
            randomList.add((1..826).random())
        }
        return api.getRandomCharacters(randomList.toString())
    }

    suspend fun getAllEpisodes(page:Int): Response<EpisodesModel> {
        return api.getAllEpisode(page)
    }

    suspend fun getCharacter(nameCharacter: String): Response<SearchModel>{
        return api.getCharacter(nameCharacter)
    }
}