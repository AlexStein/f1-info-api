package ru.softmine.f1infoapi.mvp.presenter

import com.github.terrakok.cicerone.Router
import io.reactivex.rxjava3.core.Scheduler
import moxy.InjectViewState
import moxy.MvpPresenter
import ru.softmine.f1infoapi.mvp.model.entity.DriverRanking
import ru.softmine.f1infoapi.mvp.model.entity.Season
import ru.softmine.f1infoapi.mvp.model.repository.interfaces.DriversRepository
import ru.softmine.f1infoapi.mvp.navigation.IScreens
import ru.softmine.f1infoapi.mvp.presenter.interfaces.ListPresenter
import ru.softmine.f1infoapi.mvp.view.*
import javax.inject.Inject
import javax.inject.Named

@InjectViewState
class DriverRankingsPresenter(val season: Season): MvpPresenter<DriverRankingsView>() {

    @field:Named("ui") @Inject lateinit var uiScheduler: Scheduler
    @Inject lateinit var driversRepo: DriversRepository
    @Inject lateinit var screens: IScreens
    @Inject lateinit var router: Router

    inner class DriversListPresenter : ListPresenter<DriverRankingItemView> {
        val driverRankings = mutableListOf<DriverRanking>()

        override var itemClickListener: ((DriverRankingItemView) -> Unit)? = null
        override fun getCount() = driverRankings.size
        override fun bindView(view: DriverRankingItemView) {
            val driverRanking = driverRankings[view.pos]
            view.setDriverName(driverRanking.driver.name)
            view.setDriverPhoto(driverRanking.driver.image)
            view.setTeamName(driverRanking.team.name)
            view.setTeamLogo(driverRanking.team.logo)
            view.setPosition(driverRanking.position)
            view.setPoints(driverRanking.points)
        }
    }

    val driversListPresenter = DriversListPresenter()

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        viewState.init()
        loadData()

        driversListPresenter.itemClickListener = { view ->
            val driverRanking = driversListPresenter.driverRankings[view.pos]
            router.navigateTo(screens.driver(driverRanking.driver))
        }
    }

    private fun loadData() {
        driversRepo.getDriverRankings(season)
            .observeOn(uiScheduler)
            .subscribe({ rankings ->
                driversListPresenter.driverRankings.clear()
                driversListPresenter.driverRankings.addAll(rankings)
                viewState.updateList()
            }, { it ->
                println("Error: ${it.message}")
            })
    }

    fun backClick(): Boolean {
        router.exit()
        return true
    }
}