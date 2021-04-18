package ru.softmine.f1infoapi.mvp.model.repository.cache

import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single
import ru.softmine.f1infoapi.mvp.model.entity.Team
import ru.softmine.f1infoapi.mvp.model.entity.room.RoomTeam
import ru.softmine.f1infoapi.mvp.model.entity.room.db.Database
import ru.softmine.f1infoapi.mvp.model.repository.interfaces.TeamsCache

class TeamsCacheImpl(private val db: Database) : TeamsCache {

    override fun putTeams(teams: List<Team>): Completable {
        return Completable.fromAction {
            Single.fromCallable {
                val roomTeams = teams.map { team ->
                    RoomTeam(
                        team.id,
                        team.name,
                        team.logo,
                        team.president,
                        team.director,
                        team.technical_manager,
                        team.engine,
                        team.tyres
                    )
                }
                db.teamDao.insert(roomTeams)
                teams
            }
        }
    }

    override fun getTeams(): Single<List<Team>> {
        return Single.fromCallable {
            val teams = db.teamDao.getAll().map { roomTeam ->
                Team(
                    roomTeam.id,
                    roomTeam.name,
                    roomTeam.logo,
                    roomTeam.president,
                    roomTeam.director,
                    roomTeam.technical_manager,
                    roomTeam.engine,
                    roomTeam.tyres)
            }
            teams
        }
    }

    override fun getTeam(id: Int): Single<Team> {
        return Single.fromCallable {
            db.teamDao.findById(id)?.let { roomTeam ->
                Team(
                    roomTeam.id,
                    roomTeam.name,
                    roomTeam.logo,
                    roomTeam.president,
                    roomTeam.director,
                    roomTeam.technical_manager,
                    roomTeam.engine,
                    roomTeam.tyres)
            }
        }
    }
}