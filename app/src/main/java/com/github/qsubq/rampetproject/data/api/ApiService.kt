package com.github.qsubq.rampetproject.data.api

import com.github.qsubq.rampetproject.data.model.allCharacterModel.AllCharacterModel
import com.github.qsubq.rampetproject.data.model.characterModel.CharacterModel
import com.github.qsubq.rampetproject.data.model.episodeModel.EpisodesModel
import com.github.qsubq.rampetproject.data.model.searchModel.SearchModel
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {

    @GET("character/{id}")
    suspend fun getRandomCharacters(@Path("id") randomValue: String): Response<CharacterModel>

    @GET("episode")
    suspend fun getAllEpisode(@Query("page") page: Int): Response<EpisodesModel>


    @GET("character/")
    suspend fun getCharacter(@Query("name") nameCharacter: String): Response<SearchModel>

    @GET("character/")
    suspend fun getAllCharacter(@Query("page") page: Int): Response<AllCharacterModel>
}