package ru.softmine.f1infoapi.mvp.model.repository.cache

import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single
import ru.softmine.f1infoapi.mvp.model.entity.Season
import ru.softmine.f1infoapi.mvp.model.entity.Team
import ru.softmine.f1infoapi.mvp.model.entity.TeamRanking
import ru.softmine.f1infoapi.mvp.model.entity.room.RoomTeam
import ru.softmine.f1infoapi.mvp.model.entity.room.RoomTeamRanking
import ru.softmine.f1infoapi.mvp.model.entity.room.db.Database
import ru.softmine.f1infoapi.mvp.model.repository.interfaces.cache.TeamsCache

class TeamsCacheImpl(private val db: Database) : TeamsCache {

    override fun putTeams(teams: List<Team>): Completable = Completable.fromAction {
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

    override fun putTeamRankings(teamRankings: List<TeamRanking>): Completable =
        Completable.fromAction {
            Single.fromCallable {
                val roomTeamRankings = teamRankings.map { teamRanking ->
                    RoomTeamRanking(
                        teamRanking.team.id,
                        teamRanking.position,
                        teamRanking.points,
                        teamRanking.season
                    )
                }
                db.teamRankingDao.insert(roomTeamRankings)
                teamRankings
            }
        }

    override fun getTeams(): Single<List<Team>> = Single.fromCallable {
        val teams = db.teamDao.getAll().map { roomTeam ->
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
        teams
    }

    override fun getTeam(id: Int): Single<Team> = Single.fromCallable {
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

    override fun getTeamRankings(season: Season): Single<List<TeamRanking>> = Single.fromCallable {
        val teamRankings =
            db.teamRankingDao.getSeasonRanking(seasonId = season.id).map { roomTeamRanking ->
                val team = getTeam(roomTeamRanking.teamId).blockingGet()
                TeamRanking(
                    position = roomTeamRanking.position,
                    team = team,
                    points = roomTeamRanking.points,
                    season = season.id
                )
            }
        teamRankings
    }
}