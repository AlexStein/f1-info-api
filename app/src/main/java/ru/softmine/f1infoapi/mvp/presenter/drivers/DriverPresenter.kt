package ru.softmine.f1infoapi.mvp.presenter.drivers

import com.github.terrakok.cicerone.Router
import moxy.MvpPresenter
import ru.softmine.f1infoapi.mvp.model.entity.Driver
import ru.softmine.f1infoapi.mvp.navigation.IScreens
import ru.softmine.f1infoapi.mvp.view.drivers.DriverView
import javax.inject.Inject

class DriverPresenter(private val driver: Driver): MvpPresenter<DriverView>() {

    @Inject
    lateinit var router: Router
    @Inject
    lateinit var screens: IScreens

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        viewState.init()

        viewState.setName(driver.name)
        viewState.loadImage(driver.image)
        viewState.setNationality(driver.nationality)
        viewState.setBirthdate(driver.birthdate)
    }


    fun backClick(): Boolean {
        router.exit()
        return true
    }
}