package ru.softmine.f1infoapi.di.module

import android.widget.ImageView
import dagger.Module
import dagger.Provides
import ru.softmine.f1infoapi.mvp.model.image.ImageLoader
import ru.softmine.f1infoapi.mvp.model.network.NetworkStatus
import ru.softmine.f1infoapi.mvp.model.repository.interfaces.ImageCache
import ru.softmine.f1infoapi.ui.App
import ru.softmine.f1infoapi.ui.image.GlideImageLoader
import javax.inject.Named

@Module
class ImageModule {

    @Named("location")
    @Provides
    fun location(): String = App.imageLocation

    @Provides
    fun imageLoader(
        imageCache: ImageCache,
        networkStatus: NetworkStatus,
        @Named("location") location: String
    ): ImageLoader<ImageView> = GlideImageLoader(imageCache, networkStatus, location)
}