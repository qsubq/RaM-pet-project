package com.github.qsubq.rampetproject.data.api

import com.github.qsubq.rampetproject.model.characterModel.CharacterModel
import com.github.qsubq.rampetproject.model.episodeModel.EpisodesModel
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {

    @GET("character/{id}")
    suspend fun getRandomCharacters(@Path("id") randomValue : String) : Response<CharacterModel>

    @GET("episode")
    suspend fun getAllEpisode() : Response<EpisodesModel>
}