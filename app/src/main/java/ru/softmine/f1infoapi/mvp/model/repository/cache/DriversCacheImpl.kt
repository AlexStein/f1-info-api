package ru.softmine.f1infoapi.mvp.model.repository.cache

import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single
import ru.softmine.f1infoapi.mvp.model.entity.Driver
import ru.softmine.f1infoapi.mvp.model.entity.DriverRanking
import ru.softmine.f1infoapi.mvp.model.entity.Season
import ru.softmine.f1infoapi.mvp.model.entity.room.db.Database
import ru.softmine.f1infoapi.mvp.model.repository.interfaces.DriversCache

class DriversCacheImpl(private val db: Database) : DriversCache {
    override fun putDrivers(drivers: List<Driver>): Completable {
        TODO("Not yet implemented")
    }

    override fun putDriverRankings(driverRankings: List<DriverRanking>): Completable {
        TODO("Not yet implemented")
    }

    override fun getDrivers(): Single<List<Driver>> {
        TODO("Not yet implemented")
    }

    override fun getDriver(id: Int): Single<Driver> {
        TODO("Not yet implemented")
    }

    override fun getDriverRankings(season: Season): Single<List<DriverRanking>> {
        TODO("Not yet implemented")
    }
}