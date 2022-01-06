package com.clover.harish.repository.datasource

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.clover.harish.models.CharacterVO
import com.clover.harish.network.APIServices

private const val STARTING_PAGE_INDEX = 1
private const val MAX_PAGE_COUNT = 42


class CharacterPagingSource(val service: APIServices) : PagingSource<Int, CharacterVO>() {
    override fun getRefreshKey(state: PagingState<Int, CharacterVO>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, CharacterVO> {
        return try {
            val nextPageNumber = params.key ?: STARTING_PAGE_INDEX
            val response = service.getCharacterByPage(nextPageNumber)
            LoadResult.Page(
                data = response.results,
                prevKey = if (nextPageNumber > 0) nextPageNumber - 1 else null,
                nextKey = if (nextPageNumber < MAX_PAGE_COUNT) nextPageNumber + 1 else null
            )
        } catch (e: Exception) {
            LoadResult.Error(e)
        }

    }
}