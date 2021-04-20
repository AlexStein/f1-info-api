package ru.softmine.f1infoapi.di

import dagger.Component
import ru.geekbrains.geekbrains_popular_libraries_kotlin.di.module.RepoModule
import ru.softmine.f1infoapi.di.module.*
import ru.softmine.f1infoapi.ui.activity.MainActivity
import ru.softmine.f1infoapi.di.module.ImageModule
import ru.softmine.f1infoapi.mvp.presenter.*
import ru.softmine.f1infoapi.mvp.presenter.circuits.CircuitPresenter
import ru.softmine.f1infoapi.mvp.presenter.circuits.CircuitsPresenter
import ru.softmine.f1infoapi.mvp.presenter.drivers.DriverPresenter
import ru.softmine.f1infoapi.mvp.presenter.drivers.DriverRankingsPresenter
import ru.softmine.f1infoapi.mvp.presenter.seasons.SeasonPresenter
import ru.softmine.f1infoapi.mvp.presenter.seasons.SeasonsPresenter
import ru.softmine.f1infoapi.mvp.presenter.teams.TeamPresenter
import ru.softmine.f1infoapi.mvp.presenter.teams.TeamRankingsPresenter
import ru.softmine.f1infoapi.mvp.presenter.teams.TeamsPresenter
import ru.softmine.f1infoapi.ui.adapter.*
import ru.softmine.f1infoapi.ui.fragments.circuits.CircuitFragment
import ru.softmine.f1infoapi.ui.fragments.drivers.DriverFragment
import ru.softmine.f1infoapi.ui.fragments.teams.TeamFragment
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        ApiModule::class,
        AppModule::class,
        CacheModule::class,
        CiceroneModule::class,
        ImageModule::class,
        RepoModule::class,
        SchedulerModule::class
    ]
)

interface AppComponent {
    fun inject(mainActivity: MainActivity)
    fun inject(mainPresenter: MainPresenter)
    fun inject(circuitsPresenter: CircuitsPresenter)
    fun inject(circuitsAdapter: CircuitsAdapter)
    fun inject(circuitPresenter: CircuitPresenter)
    fun inject(circuitFragment: CircuitFragment)
    fun inject(driverPresenter: DriverPresenter)
    fun inject(driverFragment: DriverFragment)
    fun inject(driverRankingsPresenter: DriverRankingsPresenter)
    fun inject(driverRankingsAdapter: DriverRankingsAdapter)
    fun inject(seasonsPresenter: SeasonsPresenter)
    fun inject(seasonsAdapter: SeasonsAdapter)
    fun inject(seasonPresenter: SeasonPresenter)
    fun inject(teamsPresenter: TeamsPresenter)
    fun inject(teamsAdapter: TeamsAdapter)
    fun inject(teamRankingsAdapter: TeamRankingsAdapter)
    fun inject(teamRankingsPresenter: TeamRankingsPresenter)
    fun inject(teamPresenter: TeamPresenter)
    fun inject(teamFragment: TeamFragment)
    fun inject(startPresenter: StartPresenter)
}
