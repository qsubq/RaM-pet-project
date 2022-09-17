package com.github.qsubq.rampetproject.data.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.github.qsubq.rampetproject.data.api.ApiService
import com.github.qsubq.rampetproject.data.model.episodeModel.Result

class EpisodesPagingSource(private val apiService: ApiService) : PagingSource<Int, Result>() {
    override fun getRefreshKey(state: PagingState<Int, Result>): Int? {
        return null
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Result> {
        return try {
            val currentPage = params.key ?: 1
            val response = apiService.getAllEpisode(currentPage)
            val data = response.body()?.results ?: emptyList()
            val responseData = mutableListOf<Result>()
            responseData.addAll(data)

            LoadResult.Page(
                data = responseData,
                prevKey = if (currentPage == 1) null else -1,
                nextKey = currentPage.plus(1)
            )
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }
}