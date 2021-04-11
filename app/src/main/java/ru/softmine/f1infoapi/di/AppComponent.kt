package ru.softmine.f1infoapi.di

import dagger.Component
import ru.geekbrains.geekbrains_popular_libraries_kotlin.di.module.RepoModule
import ru.softmine.f1infoapi.di.module.*
import ru.softmine.f1infoapi.mvp.presenter.CircuitPresenter
import ru.softmine.f1infoapi.mvp.presenter.CircuitsPresenter
import ru.softmine.f1infoapi.mvp.presenter.MainPresenter
import ru.softmine.f1infoapi.mvp.presenter.StartPresenter
import ru.softmine.f1infoapi.ui.activity.MainActivity
import ru.softmine.f1infoapi.ui.adapter.CircuitsAdapter
import ru.softmine.f1infoapi.di.module.ImageModule
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
    fun inject(circuitPresenter: CircuitPresenter)
    fun inject(circuitsAdapter: CircuitsAdapter)
    fun inject(startPresenter: StartPresenter)
}
