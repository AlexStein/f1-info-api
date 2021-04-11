package ru.softmine.f1infoapi.mvp.model.api

import io.reactivex.rxjava3.core.Single
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
import ru.softmine.f1infoapi.mvp.model.entity.*


interface DataSource {

    @GET("competitions")
    fun getCompetitions(): Single<List<Competition>>

    @GET("circuits")
    fun getCircuits(): Single<List<Circuit>>

    @GET("circuits/{id}")
    fun getCircuit(@Path("id") id: Int): Single<Circuit>

    @GET("drivers/{id}")
    fun getDriver(@Path("id") id: String): Single<Driver>

    @GET("rankings/drivers")
    fun getDriverRankings(@Query("season") season: Int): Single<DriverRanking>

    @GET("teams")
    fun getTeams(): Single<List<Team>>

    @GET("rankings/teams")
    fun getTeamRankings(@Query("season") season: Int): Single<TeamRanking>

    @GET("teams/{id}")
    fun getTeam(@Path("id") id: Int): Single<Team>

    @GET("races")
    fun getRaces(
        @Query("season") season: Int,
        @Query("competition") competition: Int
    ): Single<List<Race>>

}