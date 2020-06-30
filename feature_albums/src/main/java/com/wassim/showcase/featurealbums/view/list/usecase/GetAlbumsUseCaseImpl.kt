package com.wassim.showcase.featurealbums.view.list.usecase

import com.wassim.showcase.data.Album
import com.wassim.showcase.data.remote.ApiService
import com.wassim.showcase.utils.Result
import com.wassim.showcase.utils.safeApiCall
import kotlinx.coroutines.Dispatchers
import javax.inject.Inject

class GetAlbumsUseCaseImpl @Inject constructor(
    private val apiService: ApiService
) : GetAlbumsUseCase {

    override suspend fun invoke(): Result<List<Album>> =
        safeApiCall(Dispatchers.IO) {
            apiService.searchAlbum(
                SEARCH_ITEM,
                limit = LIMIT
            ).albums()
        }

    companion object {
        const val LIMIT = 100
        const val SEARCH_ITEM = "the"
    }
}
