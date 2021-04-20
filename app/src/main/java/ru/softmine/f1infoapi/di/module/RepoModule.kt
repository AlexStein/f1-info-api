package ru.geekbrains.geekbrains_popular_libraries_kotlin.di.module

import dagger.Module
import dagger.Provides
import ru.softmine.f1infoapi.mvp.model.api.DataSource
import ru.softmine.f1infoapi.mvp.model.network.NetworkStatus
import ru.softmine.f1infoapi.mvp.model.repository.RetrofitCircuitsRepository
import ru.softmine.f1infoapi.mvp.model.repository.RetrofitDriversRepository
import ru.softmine.f1infoapi.mvp.model.repository.RetrofitSeasonsRepository
import ru.softmine.f1infoapi.mvp.model.repository.RetrofitTeamsRepository
import ru.softmine.f1infoapi.mvp.model.repository.interfaces.*
import ru.softmine.f1infoapi.mvp.model.repository.interfaces.cache.CircuitsCache
import ru.softmine.f1infoapi.mvp.model.repository.interfaces.cache.DriversCache
import ru.softmine.f1infoapi.mvp.model.repository.interfaces.cache.SeasonsCache
import ru.softmine.f1infoapi.mvp.model.repository.interfaces.cache.TeamsCache
import javax.inject.Singleton

@Module
class RepoModule {
    @Singleton
    @Provides
    fun circuitsRepo(
        api: DataSource,
        networkStatus: NetworkStatus,
        cache: CircuitsCache
    ): CircuitsRepository = RetrofitCircuitsRepository(api, networkStatus, cache)

    @Singleton
    @Provides
    fun driversRepo(
        api: DataSource,
        networkStatus: NetworkStatus,
        cache: DriversCache
    ): DriversRepository = RetrofitDriversRepository(api, networkStatus, cache)

    @Singleton
    @Provides
    fun seasonsRepo(
        api: DataSource,
        networkStatus: NetworkStatus,
        cache: SeasonsCache
    ): SeasonsRepository = RetrofitSeasonsRepository(api, networkStatus, cache)

    @Singleton
    @Provides
    fun teamsRepo(
        api: DataSource,
        networkStatus: NetworkStatus,
        cache: TeamsCache
    ): TeamsRepository = RetrofitTeamsRepository(api, networkStatus, cache)
}