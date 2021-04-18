package ru.softmine.f1infoapi.di.module

import androidx.room.Room
import dagger.Module
import dagger.Provides
import ru.softmine.f1infoapi.mvp.model.repository.interfaces.CircuitsCache
import ru.softmine.f1infoapi.mvp.model.entity.room.db.Database
import ru.softmine.f1infoapi.mvp.model.repository.cache.CircuitsCacheImpl
import ru.softmine.f1infoapi.mvp.model.repository.cache.ImageCacheImpl
import ru.softmine.f1infoapi.mvp.model.repository.cache.SeasonsCacheImpl
import ru.softmine.f1infoapi.mvp.model.repository.cache.TeamsCacheImpl
import ru.softmine.f1infoapi.mvp.model.repository.interfaces.ImageCache
import ru.softmine.f1infoapi.mvp.model.repository.interfaces.SeasonsCache
import ru.softmine.f1infoapi.mvp.model.repository.interfaces.TeamsCache
import ru.softmine.f1infoapi.ui.App
import javax.inject.Singleton

@Module
class CacheModule {

    @Singleton
    @Provides
    fun database(app: App) = Room.databaseBuilder(app, Database::class.java, Database.DB_NAME)
        .build()

    @Singleton
    @Provides
    fun circuitsCache(db: Database): CircuitsCache = CircuitsCacheImpl(db)

    @Singleton
    @Provides
    fun imageCache(db: Database): ImageCache = ImageCacheImpl(db)

    @Singleton
    @Provides
    fun seasonsCache(db: Database): SeasonsCache = SeasonsCacheImpl(db)

    @Singleton
    @Provides
    fun teamsCache(db: Database): TeamsCache = TeamsCacheImpl(db)
}