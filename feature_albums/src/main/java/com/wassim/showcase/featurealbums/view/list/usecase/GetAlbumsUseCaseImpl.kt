package com.wassim.showcase.featurealbums.view.list.usecase

import com.wassim.showcase.core.data.remote.Album
import com.wassim.showcase.core.data.remote.ApiService
import com.wassim.showcase.utils.Result
import com.wassim.showcase.utils.safeApiCall
import javax.inject.Inject
import kotlinx.coroutines.Dispatchers

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
