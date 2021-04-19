package ru.softmine.f1infoapi.ui.navigation

import com.github.terrakok.cicerone.Screen
import com.github.terrakok.cicerone.androidx.FragmentScreen
import ru.softmine.f1infoapi.mvp.model.entity.Circuit
import ru.softmine.f1infoapi.mvp.model.entity.Driver
import ru.softmine.f1infoapi.mvp.model.entity.Season
import ru.softmine.f1infoapi.mvp.model.entity.Team
import ru.softmine.f1infoapi.mvp.navigation.IScreens
import ru.softmine.f1infoapi.ui.fragments.*


class AndroidScreens : IScreens {
    override fun start() = FragmentScreen { StartFragment.newInstance() }
    override fun circuits() = FragmentScreen { CircuitsFragment.newInstance() }
    override fun circuit(circuit: Circuit) = FragmentScreen { CircuitFragment.newInstance(circuit) }
    override fun driver(driver: Driver): Screen {
        TODO("Not yet implemented")
    }
    override fun seasons() = FragmentScreen { SeasonsFragment.newInstance() }
    override fun season(season: Season) = FragmentScreen { SeasonFragment.newInstance(season) }
    override fun teams() = FragmentScreen { TeamsFragment.newInstance() }
    override fun team(team: Team) = FragmentScreen { TeamFragment.newInstance(team) }
    override fun driverRankings(season: Season) = FragmentScreen { DriverRankingsFragment.newInstance(season) }
    override fun teamRankings(season: Season) = FragmentScreen { TeamRankingsFragment.newInstance(season) }
}