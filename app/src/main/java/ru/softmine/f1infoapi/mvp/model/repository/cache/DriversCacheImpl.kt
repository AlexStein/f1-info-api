package ru.softmine.f1infoapi.mvp.model.repository.cache

import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single
import ru.softmine.f1infoapi.mvp.model.entity.*
import ru.softmine.f1infoapi.mvp.model.entity.room.RoomDriver
import ru.softmine.f1infoapi.mvp.model.entity.room.RoomDriverRanking
import ru.softmine.f1infoapi.mvp.model.entity.room.db.Database
import ru.softmine.f1infoapi.mvp.model.repository.interfaces.cache.DriversCache

class DriversCacheImpl(private val db: Database) : DriversCache {

    override fun putDrivers(drivers: List<Driver>): Completable =
        Completable.fromAction {
            Single.fromCallable {
                val roomDrivers = drivers.map { driver ->
                    RoomDriver(
                        driver.id,
                        driver.name,
                        driver.image,
                        driver.nationality,
                        driver.birthdate
                    )
                }
                db.driverDao.insert(roomDrivers)
                drivers
            }
        }

    override fun putDriverRankings(driverRankings: List<DriverRanking>): Completable =
        Completable.fromAction {
            Single.fromCallable {
                val roomDriverRankings = driverRankings.map { driverRanking ->
                    RoomDriverRanking(
                        driverRanking.driver.id,
                        driverRanking.team.id,
                        driverRanking.position,
                        driverRanking.points,
                        driverRanking.wins,
                        driverRanking.behind,
                        driverRanking.season
                    )
                }
                db.driverRankingDao.insert(roomDriverRankings)
                driverRankings
            }
        }


    override fun getDrivers(): Single<List<Driver>> = Single.fromCallable {
        val drivers = db.driverDao.getAll().map { roomDriver ->
            Driver(
                roomDriver.id,
                roomDriver.name,
                roomDriver.image,
                roomDriver.nationality,
                roomDriver.birthdate
            )
        }
        drivers
    }

    override fun getDriver(id: Int): Single<Driver> = Single.fromCallable {
        db.driverDao.findById(id)?.let { roomDriver ->
            Driver(
                roomDriver.id,
                roomDriver.name,
                roomDriver.image,
                roomDriver.nationality,
                roomDriver.birthdate
            )
        }
    }

    private fun getTeam(id: Int): Single<Team> = Single.fromCallable {
        db.teamDao.findById(id)?.let { roomTeam ->
            Team(
                roomTeam.id,
                roomTeam.name,
                roomTeam.logo,
                roomTeam.president,
                roomTeam.director,
                roomTeam.technical_manager,
                roomTeam.engine,
                roomTeam.tyres
            )
        }
    }

    override fun getDriverRankings(season: Season): Single<List<DriverRanking>> =
        Single.fromCallable {
            val driverRankings =
                db.driverRankingDao.getSeasonRanking(seasonId = season.id)
                    .map { roomDriverRanking ->
                        val team = getTeam(roomDriverRanking.teamId).blockingGet()
                        val driver = getDriver(roomDriverRanking.driverId).blockingGet()
                        DriverRanking(
                            roomDriverRanking.position,
                            driver,
                            team,
                            roomDriverRanking.points,
                            roomDriverRanking.wins,
                            roomDriverRanking.behind,
                            roomDriverRanking.seasonId
                        )
                    }
            driverRankings
        }
}