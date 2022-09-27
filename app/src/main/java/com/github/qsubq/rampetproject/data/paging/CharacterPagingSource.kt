package com.github.qsubq.rampetproject.data.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.github.qsubq.rampetproject.data.model.allCharacterModel.Result
import com.github.qsubq.rampetproject.domain.useCase.GetAllCharacterUseCase

class CharacterPagingSource(private val useCase: GetAllCharacterUseCase) :
    PagingSource<Int, Result>() {
    override fun getRefreshKey(state: PagingState<Int, Result>): Int? {
        return null
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Result> {
        return try {
            val currentPage = params.key ?: 1
            val response = useCase.execute(currentPage)
            val data = response.body()?.results
            val responseData = mutableListOf<Result>()
            if (data != null) {
                responseData.addAll(data)
            }

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