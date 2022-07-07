package com.github.qsubq.rampetproject.data.repository

import com.github.qsubq.rampetproject.data.api.ApiService
import com.github.qsubq.rampetproject.model.characterModel.CharacterModel
import com.github.qsubq.rampetproject.model.episodeModel.EpisodesModel
import retrofit2.Response

class Repository(private val api: ApiService) {

    suspend fun getRandomCharacters(): Response<CharacterModel> {
        val randomList = mutableListOf<Int>()

        for (i in 0..20) {
            randomList.add((1..826).random())
        }

        return api.getRandomCharacters(randomList.toString())
    }

    suspend fun getAllEpisodes(): Response<EpisodesModel> {
        return api.getAllEpisode()
    }


}