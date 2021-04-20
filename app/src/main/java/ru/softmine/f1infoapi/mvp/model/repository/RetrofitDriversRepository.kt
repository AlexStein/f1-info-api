package ru.softmine.f1infoapi.mvp.model.repository

import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers
import ru.softmine.f1infoapi.mvp.model.api.DataSource
import ru.softmine.f1infoapi.mvp.model.entity.Driver
import ru.softmine.f1infoapi.mvp.model.entity.DriverRanking
import ru.softmine.f1infoapi.mvp.model.entity.Season
import ru.softmine.f1infoapi.mvp.model.network.NetworkStatus
import ru.softmine.f1infoapi.mvp.model.repository.interfaces.cache.DriversCache
import ru.softmine.f1infoapi.mvp.model.repository.interfaces.DriversRepository

class RetrofitDriversRepository (
    private val api: DataSource,
    private val networkStatus: NetworkStatus,
    private val driversCache: DriversCache
    ) : DriversRepository {

    override fun getDriverRankings(season: Season): Single<List<DriverRanking>> =
        networkStatus.isOnlineSingle().flatMap { isOnline ->
            if (isOnline) {
                api.getDriverRankings(season.id).flatMap { result ->
                    val driversRankings = result.response.map { parcelable ->
                        parcelable
                    }
                    driversCache.putDriverRankings(driversRankings).andThen(Single.just(driversRankings))
                }
            } else {
                driversCache.getDriverRankings(season)
            }
        }.subscribeOn(Schedulers.io())

    override fun getDriver(id: Int): Single<Driver> =
        networkStatus.isOnlineSingle().flatMap { isOnline ->
            if (isOnline) {
                api.getDriver(id).flatMap { result ->
                    val drivers = result.response.let {
                        it[0]
                    }
                    driversCache.putDrivers(listOf(drivers)).andThen(Single.just(drivers))
                }
            } else {
                driversCache.getDriver(id)
            }
        }.subscribeOn(Schedulers.io())
}

