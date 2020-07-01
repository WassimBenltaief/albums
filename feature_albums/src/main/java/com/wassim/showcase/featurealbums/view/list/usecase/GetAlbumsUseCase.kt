package com.wassim.showcase.featurealbums.view.list.usecase

import com.wassim.showcase.core.data.remote.Album
import com.wassim.showcase.utils.Result

interface GetAlbumsUseCase {
    suspend operator fun invoke(): Result<List<Album>>
}
