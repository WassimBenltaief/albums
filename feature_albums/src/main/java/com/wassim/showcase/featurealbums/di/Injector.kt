package com.wassim.showcase.featurealbums.di

import com.wassim.showcase.core.di.coreInstance
import com.wassim.showcase.featurealbums.view.item.view.AlbumFragment
import com.wassim.showcase.featurealbums.view.list.view.AlbumListFragment

fun inject(fragment: AlbumListFragment) {
    DaggerAlbumsComponent
        .factory()
        .create(coreInstance(fragment))
        .inject(fragment)
}

fun inject(fragment: AlbumFragment) {
    DaggerAlbumsComponent
        .factory()
        .create(coreInstance(fragment))
        .inject(fragment)
}