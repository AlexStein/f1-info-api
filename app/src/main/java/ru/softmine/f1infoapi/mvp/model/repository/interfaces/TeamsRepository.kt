package ru.softmine.f1infoapi.mvp.model.repository.interfaces

import io.reactivex.rxjava3.core.Single
import ru.softmine.f1infoapi.mvp.model.entity.Team

interface TeamsRepository {
    fun getTeams(): Single<List<Team>>
    fun getTeam(id: Int): Single<Team>
}