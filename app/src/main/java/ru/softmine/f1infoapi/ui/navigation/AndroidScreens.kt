package ru.softmine.f1infoapi.ui.navigation

import com.github.terrakok.cicerone.androidx.FragmentScreen
import ru.softmine.f1infoapi.mvp.model.entity.Circuit
import ru.softmine.f1infoapi.mvp.navigation.IScreens
import ru.softmine.f1infoapi.ui.fragments.CircuitFragment
import ru.softmine.f1infoapi.ui.fragments.CircuitsFragment
import ru.softmine.f1infoapi.ui.fragments.StartFragment


class AndroidScreens : IScreens {
    override fun start() = FragmentScreen { StartFragment.newInstance() }
    override fun circuits() = FragmentScreen { CircuitsFragment.newInstance() }
    override fun circuit(circuit: Circuit) = FragmentScreen { CircuitFragment.newInstance(circuit) }
}