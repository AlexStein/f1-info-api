package ru.softmine.f1infoapi.mvp.model.repository.cache

import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single
import ru.softmine.f1infoapi.mvp.model.entity.Season
import ru.softmine.f1infoapi.mvp.model.entity.room.RoomSeason
import ru.softmine.f1infoapi.mvp.model.entity.room.db.Database
import ru.softmine.f1infoapi.mvp.model.repository.interfaces.SeasonsCache

class SeasonsCacheImpl(private val db: Database) : SeasonsCache {

    override fun putSeasons(seasons: List<Season>): Completable {
        return Completable.fromAction {
            Single.fromCallable {
                val roomSeasons = seasons.map { season ->
                    RoomSeason(season.id)
                }
                db.seasonDao.insert(roomSeasons)
                seasons
            }
        }
    }

    override fun getSeasons(): Single<List<Season>> {
        return Single.fromCallable {
            val seasons = db.seasonDao.getAll().map { roomSeason ->
                Season(roomSeason.id)
            }
            seasons
        }
    }
}
