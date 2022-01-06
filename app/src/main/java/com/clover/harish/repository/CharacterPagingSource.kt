//package com.clover.harish.repository
//
//import com.clover.harish.models.CharacterVO
//import androidx.paging.PagingSource
//import androidx.paging.PagingState
//import com.clover.harish.models.response.CharacterResponseVO
//import com.clover.harish.network.APIServices
//import retrofit2.HttpException
//import java.io.IOException
//
//private const val GITHUB_STARTING_PAGE_INDEX = 1
//
//
//class CharacterPagingSource(val service: APIServices) : PagingSource<Int, CharacterVO>() {
//    override fun getRefreshKey(state: PagingState<Int, CharacterVO>): Int? {
//        return state.anchorPosition?.let { anchorPosition ->
//            state.closestPageToPosition(anchorPosition)?.prevKey?.plus(1)
//                ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)
//        }
//    }
//
//    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, CharacterVO> {
//        val position = params.key ?: GITHUB_STARTING_PAGE_INDEX
//
//        return try {
//            val response: CharacterResponseVO = service.getCharacters(position)
//            val results = response.results
//            val nextKey = if (response.results.isEmpty()) {
//                null
//            } else {
//                position + (params.loadSize / CharacterRepository.NETWORK_PAGE_SIZE)
//            }
//            LoadResult.Page(
//                data = results,
//                prevKey = if (position == GITHUB_STARTING_PAGE_INDEX) null else position - 1,
//                nextKey = nextKey
//            )
//        } catch (exception: IOException) {
//            return LoadResult.Error(exception)
//        } catch (exception: HttpException) {
//            return LoadResult.Error(exception)
//        }
//    }
//}