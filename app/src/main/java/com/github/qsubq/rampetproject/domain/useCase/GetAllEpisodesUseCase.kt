package com.github.qsubq.rampetproject.domain.useCase

import com.github.qsubq.rampetproject.data.model.episodeModel.EpisodesModel
import com.github.qsubq.rampetproject.domain.repository.RemoteRepository
import retrofit2.Response

class GetAllEpisodesUseCase(private val repo: RemoteRepository) {
    suspend fun execute(page: Int) = repo.getAllEpisodes(page)
    }
