package ru.softmine.f1infoapi.mvp.model.repository.interfaces

import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single
import ru.softmine.f1infoapi.mvp.model.entity.*

interface DriversCache {
    fun putDrivers(drivers: List<Driver>): Completable
    fun putDriverRankings(driverRankings: List<DriverRanking>): Completable
    fun getDrivers(): Single<List<Driver>>
    fun getDriver(id: Int): Single<Driver>
    fun getDriverRankings(season: Season):  Single<List<DriverRanking>>
}