package ru.softmine.f1infoapi.mvp.model.repository.interfaces

import io.reactivex.rxjava3.core.Single
import ru.softmine.f1infoapi.mvp.model.entity.Season
import ru.softmine.f1infoapi.mvp.model.entity.Team
import ru.softmine.f1infoapi.mvp.model.entity.TeamRanking

interface TeamsRepository {
    fun getTeams(): Single<List<Team>>
    fun getTeamRankings(season: Season): Single<List<TeamRanking>>
    fun getTeam(id: Int): Single<Team>
}