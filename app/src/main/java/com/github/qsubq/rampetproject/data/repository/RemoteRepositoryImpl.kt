package com.github.qsubq.rampetproject.data.repository

import com.github.qsubq.rampetproject.data.api.ApiService
import com.github.qsubq.rampetproject.data.model.allCharacterModel.AllCharacterModel
import com.github.qsubq.rampetproject.data.model.characterModel.CharacterModel
import com.github.qsubq.rampetproject.data.model.episodeModel.EpisodesModel
import com.github.qsubq.rampetproject.data.model.searchModel.SearchModel
import com.github.qsubq.rampetproject.domain.repository.RemoteRepository
import retrofit2.HttpException
import retrofit2.Response

class RemoteRepositoryImpl(private val api: ApiService) : RemoteRepository {

    override suspend fun getRandomCharacter(): NetworkResult<CharacterModel> {
        val randomList = mutableListOf<Int>()
        for (i in 0..20) {
            randomList.add((1..826).random())
        }
        return handleApi { api.getRandomCharacters(randomList.toString()) }
    }

    override suspend fun getCharacterFromSearch(nameCharacter: String): NetworkResult<SearchModel> {
        return handleApi { api.getCharacter(nameCharacter) }
    }

    override suspend fun getAllCharacter(page: Int): Response<AllCharacterModel> {
        return api.getAllCharacter(page)
    }

    override suspend fun getAllEpisodes(page: Int): Response<EpisodesModel> {
        return api.getAllEpisode(page)
    }

    private suspend fun <T : Any> handleApi(
        execute: suspend () -> Response<T>,
    ): NetworkResult<T> {
        return try {
            val response = execute()
            val body = response.body()
            if (response.isSuccessful && body != null) {
                NetworkResult.Success(body)
            } else {
                NetworkResult.Error(code = response.code(), message = response.message())
            }
        } catch (e: HttpException) {
            NetworkResult.Error(code = e.code(), message = e.message())
        } catch (e: Throwable) {
            NetworkResult.Exception(e)
        }
    }

    sealed class NetworkResult<T : Any> {
        class Success<T : Any>(val data: T) : NetworkResult<T>()
        class Error<T : Any>(val code: Int, val message: String?) : NetworkResult<T>()
        class Exception<T : Any>(val e: Throwable) : NetworkResult<T>()
    }
}