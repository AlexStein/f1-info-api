package ru.softmine.f1infoapi.mvp.model.api

import io.reactivex.rxjava3.core.Single
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
import ru.softmine.f1infoapi.mvp.model.entity.*


interface DataSource {

    @GET("competitions")
    fun getCompetitions(): Single<ResponseObject<Competition>>

    @GET("circuits")
    fun getCircuits(): Single<ResponseObject<Circuit>>

    @GET("circuits/{id}")
    fun getCircuit(@Path("id") id: Int): Single<ResponseObject<Circuit>>

    @GET("drivers/{id}")
    fun getDriver(@Path("id") id: Int): Single<ResponseObject<Driver>>

    @GET("rankings/drivers")
    fun getDriverRankings(@Query("season") season: Int): Single<ResponseObject<DriverRanking>>

    @GET("teams")
    fun getTeams(): Single<ResponseObject<Team>>

    @GET("rankings/teams")
    fun getTeamRankings(@Query("season") season: Int): Single<ResponseObject<TeamRanking>>

    @GET("teams/{id}")
    fun getTeam(@Path("id") id: Int): Single<ResponseObject<Team>>

    @GET("races")
    fun getRaces(
        @Query("season") season: Int,
        @Query("competition") competition: Int
    ): Single<ResponseObject<Race>>

    @GET("seasons")
    fun getSeasons(): Single<ResponseObject<Int>>

}