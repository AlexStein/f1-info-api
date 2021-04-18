package ru.softmine.f1infoapi.mvp.presenter

import com.github.terrakok.cicerone.Router
import moxy.InjectViewState
import moxy.MvpPresenter
import ru.softmine.f1infoapi.mvp.navigation.IScreens
import ru.softmine.f1infoapi.mvp.view.StartView
import javax.inject.Inject

@InjectViewState
class StartPresenter: MvpPresenter<StartView>() {

    @Inject
    lateinit var router: Router
    @Inject
    lateinit var screens: IScreens

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        viewState.init()
    }

    fun onCircuitsClicked() {
        router.navigateTo(screens.circuits())
    }

    fun onSeasonsClicked() {
        router.navigateTo(screens.seasons())
    }

    fun onTeamsClicked() {
        router.navigateTo(screens.teams())
    }

    fun backClicked() {
        router.exit()
    }

}
