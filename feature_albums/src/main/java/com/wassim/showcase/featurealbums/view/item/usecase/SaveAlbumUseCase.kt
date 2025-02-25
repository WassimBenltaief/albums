package com.wassim.showcase.featurealbums.view.item.usecase

import com.wassim.showcase.core.data.remote.Album
import com.wassim.showcase.utils.Result

interface SaveAlbumUseCase {
    suspend operator fun invoke(album: Album): Result<Long>
}
