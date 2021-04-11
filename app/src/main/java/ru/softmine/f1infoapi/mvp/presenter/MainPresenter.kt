package ru.softmine.f1infoapi.mvp.presenter

import com.github.terrakok.cicerone.Router
import moxy.InjectViewState
import moxy.MvpPresenter
import ru.softmine.f1infoapi.mvp.navigation.IScreens
import ru.softmine.f1infoapi.mvp.view.MainView
import javax.inject.Inject

@InjectViewState
class MainPresenter: MvpPresenter<MainView>() {

    @Inject
    lateinit var router: Router
    @Inject
    lateinit var screens: IScreens

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        router.newRootScreen(screens.start())
    }

    fun backClicked() {
        router.exit()
    }

}
