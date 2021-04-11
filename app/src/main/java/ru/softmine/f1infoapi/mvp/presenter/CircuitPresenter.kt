package ru.softmine.f1infoapi.mvp.presenter

import com.github.terrakok.cicerone.Router
import moxy.InjectViewState
import moxy.MvpPresenter
import ru.softmine.f1infoapi.mvp.model.entity.Circuit
import ru.softmine.f1infoapi.mvp.navigation.IScreens
import ru.softmine.f1infoapi.mvp.view.CircuitView
import javax.inject.Inject

@InjectViewState
class CircuitPresenter(private val circuit: Circuit): MvpPresenter<CircuitView>() {

    @Inject lateinit var router: Router
    @Inject lateinit var screens: IScreens

    fun backClick(): Boolean {
        router.exit()
        return true
    }
}