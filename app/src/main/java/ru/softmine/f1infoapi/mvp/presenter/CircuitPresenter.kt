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

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        viewState.init()

        viewState.setName(circuit.name)
        // viewState.setCountry(circuit.country ?: "")
        // viewState.setCity(circuit.city ?: "")
        viewState.loadImage(circuit.image)
        viewState.setLength(circuit.length)
        viewState.setOpenDate(circuit.opened)
        viewState.setCapacity(circuit.capacity)
        viewState.setOwner(circuit.owner)
    }


    fun backClick(): Boolean {
        router.exit()
        return true
    }
}