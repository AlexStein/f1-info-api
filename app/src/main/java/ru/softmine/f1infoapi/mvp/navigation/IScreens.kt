package ru.softmine.f1infoapi.mvp.navigation

import com.github.terrakok.cicerone.Screen
import ru.softmine.f1infoapi.mvp.model.entity.Circuit
import ru.softmine.f1infoapi.mvp.model.entity.Driver
import ru.softmine.f1infoapi.mvp.model.entity.Season
import ru.softmine.f1infoapi.mvp.model.entity.Team


interface IScreens {
    fun start(): Screen
    fun circuits(): Screen
    fun circuit(circuit: Circuit) : Screen
    fun driver(driver: Driver) : Screen
    fun driverRankings(season: Season) : Screen
    fun season(season: Season) : Screen
    fun seasons() : Screen
    fun team(team: Team) : Screen
    fun teams() : Screen
    fun teamRankings(season: Season) : Screen
}