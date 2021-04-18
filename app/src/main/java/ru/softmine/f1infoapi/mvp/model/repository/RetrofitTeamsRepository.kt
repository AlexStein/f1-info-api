package ru.softmine.f1infoapi.mvp.model.repository

import android.util.Log
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers
import ru.softmine.f1infoapi.mvp.model.api.DataSource
import ru.softmine.f1infoapi.mvp.model.entity.Team
import ru.softmine.f1infoapi.mvp.model.network.NetworkStatus
import ru.softmine.f1infoapi.mvp.model.repository.interfaces.TeamsCache
import ru.softmine.f1infoapi.mvp.model.repository.interfaces.TeamsRepository

class RetrofitTeamsRepository (
    private val api: DataSource,
    private val networkStatus: NetworkStatus,
    private val teamsCache: TeamsCache
    ) : TeamsRepository {

        override fun getTeams(): Single<List<Team>> =
            networkStatus.isOnlineSingle().flatMap { isOnline ->
                if (isOnline) {
                    api.getTeams().flatMap { result ->
                        Log.d("RetrofitTeamsRepository", result.toString())
                        val teams = result.response.map { parcelable ->
                            parcelable
                        }
                        teamsCache.putTeams(teams).andThen(Single.just(teams))
                    }
                } else {
                    teamsCache.getTeams()
                }
            }.subscribeOn(Schedulers.io())

    override fun getTeam(id: Int): Single<Team> =
        networkStatus.isOnlineSingle().flatMap { isOnline ->
            if (isOnline) {
                api.getTeam(id).flatMap { result ->
                    val teams = result.response.let {
                        it[0]
                    }
                    teamsCache.putTeams(listOf(teams)).andThen(Single.just(teams))
                }
            } else {
                teamsCache.getTeam(id)
            }
        }.subscribeOn(Schedulers.io())
}

