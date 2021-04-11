package ru.geekbrains.geekbrains_popular_libraries_kotlin.di.module

import dagger.Module
import dagger.Provides
import ru.softmine.f1infoapi.mvp.model.api.DataSource
import ru.softmine.f1infoapi.mvp.model.network.NetworkStatus
import ru.softmine.f1infoapi.mvp.model.repository.interfaces.CircuitsCache
import ru.softmine.f1infoapi.mvp.model.repository.RetrofitCircuitsRepository
import ru.softmine.f1infoapi.mvp.model.repository.interfaces.CircuitsRepository
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
}