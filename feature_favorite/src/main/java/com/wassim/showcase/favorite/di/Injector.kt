package com.wassim.showcase.favorite.di

import com.wassim.showcase.core.di.coreInstance
import com.wassim.showcase.favorite.view.FavoriteAlbumsFragment

fun inject(fragment: FavoriteAlbumsFragment) {
    DaggerFavoriteComponent
        .factory()
        .create(coreInstance(fragment))
        .inject(fragment)
}