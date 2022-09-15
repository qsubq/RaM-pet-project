package com.github.qsubq.rampetproject.data.api

import androidx.annotation.IntRange
import com.github.qsubq.rampetproject.data.model.characterModel.CharacterModel
import com.github.qsubq.rampetproject.data.model.episodeModel.EpisodesModel
import com.github.qsubq.rampetproject.data.model.searchModel.SearchModel
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {

    @GET("character/{id}")
    suspend fun getRandomCharacters(@Path("id") randomValue : String) : Response<CharacterModel>

    @GET("episode")
    suspend fun getAllEpisode(
        @Query("page") @IntRange(from = 1) page : Int = 1,
        @Query("pageSize") @IntRange(from = 1, to = MAX_PAGE_SIZE.toLong()) pageSize : Int = DEFAULT_PAGE_SIZE
    ) : Response<EpisodesModel>


    @GET("character/")
    suspend fun getCharacter(@Query("name") nameCharacter : String):Response<SearchModel>

    companion object {
        const val DEFAULT_PAGE_SIZE = 20
        const val MAX_PAGE_SIZE = 20
    }
}