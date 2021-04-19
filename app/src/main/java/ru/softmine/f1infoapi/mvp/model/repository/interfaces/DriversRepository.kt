package ru.softmine.f1infoapi.mvp.model.repository.interfaces

import io.reactivex.rxjava3.core.Single
import ru.softmine.f1infoapi.mvp.model.entity.Driver
import ru.softmine.f1infoapi.mvp.model.entity.DriverRanking
import ru.softmine.f1infoapi.mvp.model.entity.Season

interface DriversRepository {
    fun getDriverRankings(season: Season): Single<List<DriverRanking>>
    fun getDriver(id: Int): Single<Driver>
}