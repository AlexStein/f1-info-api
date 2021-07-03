package ru.softmine.f1infoapi.mvp.presenter.circuits

import android.util.Log
import com.github.terrakok.cicerone.Router
import io.reactivex.rxjava3.core.Scheduler
import moxy.InjectViewState
import moxy.MvpPresenter
import ru.softmine.f1infoapi.mvp.model.entity.Circuit
import ru.softmine.f1infoapi.mvp.model.repository.interfaces.CircuitsRepository
import ru.softmine.f1infoapi.mvp.navigation.IScreens
import ru.softmine.f1infoapi.mvp.presenter.interfaces.ListPresenter
import ru.softmine.f1infoapi.mvp.view.circuits.CircuitsView
import ru.softmine.f1infoapi.mvp.view.circuits.CircuitItemView
import javax.inject.Inject
import javax.inject.Named

@InjectViewState
class CircuitsPresenter: MvpPresenter<CircuitsView>() {

    @field:Named("ui") @Inject lateinit var uiScheduler: Scheduler
    @Inject lateinit var circuitsRepo: CircuitsRepository
    @Inject lateinit var screens: IScreens
    @Inject lateinit var router: Router

    inner class CircuitsListPresenter : ListPresenter<CircuitItemView> {
        val circuits = mutableListOf<Circuit>()

        override var itemClickListener: ((CircuitItemView) -> Unit)? = null
        override fun getCount() = circuits.size
        override fun bindView(view: CircuitItemView) {
            val circuit = circuits[view.pos]
            view.setName(circuit.name)
            view.loadImage(circuit.image)
        }
    }

    val circuitsListPresenter = CircuitsListPresenter()

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        viewState.init()
        loadData()

        circuitsListPresenter.itemClickListener = { view ->
            val circuit = circuitsListPresenter.circuits[view.pos]
            router.navigateTo(screens.circuit(circuit))
        }
    }

    private fun loadData() {
        circuitsRepo.getCircuits()
            .observeOn(uiScheduler)
            .subscribe({ repos ->
                Log.d("CircuitsPresenter", repos.toString())
                circuitsListPresenter.circuits.clear()
                circuitsListPresenter.circuits.addAll(repos)
                viewState.updateList()
            }, {
                println("Error: ${it.message}")
            })
    }

    fun backClick(): Boolean {
        router.exit()
        return true
    }
}