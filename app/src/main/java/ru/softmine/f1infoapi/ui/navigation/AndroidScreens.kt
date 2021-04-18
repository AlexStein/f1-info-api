package ru.softmine.f1infoapi.ui.navigation

import com.github.terrakok.cicerone.androidx.FragmentScreen
import ru.softmine.f1infoapi.mvp.model.entity.Circuit
import ru.softmine.f1infoapi.mvp.model.entity.Team
import ru.softmine.f1infoapi.mvp.navigation.IScreens
import ru.softmine.f1infoapi.ui.fragments.*


class AndroidScreens : IScreens {
    override fun start() = FragmentScreen { StartFragment.newInstance() }
    override fun circuits() = FragmentScreen { CircuitsFragment.newInstance() }
    override fun circuit(circuit: Circuit) = FragmentScreen { CircuitFragment.newInstance(circuit) }
    override fun seasons() = FragmentScreen { SeasonsFragment.newInstance() }
    override fun teams() = FragmentScreen { TeamsFragment.newInstance() }
    override fun team(team: Team) = FragmentScreen { TeamFragment.newInstance(team) }
}