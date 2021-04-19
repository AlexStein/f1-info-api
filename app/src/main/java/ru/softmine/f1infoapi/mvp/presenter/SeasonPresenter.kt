package ru.softmine.f1infoapi.mvp.presenter

import com.github.terrakok.cicerone.Router
import moxy.InjectViewState
import moxy.MvpPresenter
import ru.softmine.f1infoapi.mvp.model.entity.Season
import ru.softmine.f1infoapi.mvp.navigation.IScreens
import ru.softmine.f1infoapi.mvp.view.SeasonView
import javax.inject.Inject

@InjectViewState
class SeasonPresenter(val season: Season): MvpPresenter<SeasonView>() {

    @Inject lateinit var router: Router
    @Inject lateinit var screens: IScreens

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        viewState.init()
    }

    fun onDriversClicked() {
        router.navigateTo(screens.driverRankings(season))
    }

    fun onTeamsClicked() {
        router.navigateTo(screens.teamRankings(season))
    }

    fun backClicked() {
        router.exit()
    }
}
