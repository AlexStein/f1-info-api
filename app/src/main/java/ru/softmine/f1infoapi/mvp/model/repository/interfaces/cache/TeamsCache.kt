package ru.softmine.f1infoapi.mvp.model.repository.interfaces.cache

import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single
import ru.softmine.f1infoapi.mvp.model.entity.Season
import ru.softmine.f1infoapi.mvp.model.entity.Team
import ru.softmine.f1infoapi.mvp.model.entity.TeamRanking

interface TeamsCache {
    fun putTeams(teams: List<Team>): Completable
    fun putTeamRankings(teamRankings: List<TeamRanking>): Completable
    fun getTeams(): Single<List<Team>>
    fun getTeam(id: Int): Single<Team>
    fun getTeamRankings(season: Season):  Single<List<TeamRanking>>
}