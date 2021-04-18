package ru.softmine.f1infoapi.di

import dagger.Component
import ru.geekbrains.geekbrains_popular_libraries_kotlin.di.module.RepoModule
import ru.softmine.f1infoapi.di.module.*
import ru.softmine.f1infoapi.ui.activity.MainActivity
import ru.softmine.f1infoapi.ui.adapter.CircuitsAdapter
import ru.softmine.f1infoapi.di.module.ImageModule
import ru.softmine.f1infoapi.mvp.presenter.*
import ru.softmine.f1infoapi.ui.adapter.SeasonsAdapter
import ru.softmine.f1infoapi.ui.adapter.TeamsAdapter
import ru.softmine.f1infoapi.ui.fragments.CircuitFragment
import ru.softmine.f1infoapi.ui.fragments.TeamFragment
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
    fun inject(seasonsPresenter: SeasonsPresenter)
    fun inject(seasonsAdapter: SeasonsAdapter)
    fun inject(teamsPresenter: TeamsPresenter)
    fun inject(teamsAdapter: TeamsAdapter)
    fun inject(teamPresenter: TeamPresenter)
    fun inject(teamFragment: TeamFragment)
    fun inject(startPresenter: StartPresenter)
}
