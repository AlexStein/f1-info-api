package ru.softmine.f1infoapi.mvp.presenter.seasons

import android.util.Log
import com.github.terrakok.cicerone.Router
import io.reactivex.rxjava3.core.Scheduler
import moxy.MvpPresenter
import ru.softmine.f1infoapi.mvp.model.entity.Season
import ru.softmine.f1infoapi.mvp.model.repository.interfaces.SeasonsRepository
import ru.softmine.f1infoapi.mvp.navigation.IScreens
import ru.softmine.f1infoapi.mvp.presenter.interfaces.ListPresenter
import ru.softmine.f1infoapi.mvp.view.seasons.SeasonItemView
import ru.softmine.f1infoapi.mvp.view.seasons.SeasonsView
import javax.inject.Inject
import javax.inject.Named

class SeasonsPresenter : MvpPresenter<SeasonsView>() {
    @field:Named("ui") @Inject lateinit var uiScheduler: Scheduler
    @Inject lateinit var teamsRepo: SeasonsRepository
    @Inject lateinit var screens: IScreens
    @Inject lateinit var router: Router

    inner class SeasonsListPresenter : ListPresenter<SeasonItemView> {
        val seasons = mutableListOf<Season>()

        override var itemClickListener: ((SeasonItemView) -> Unit)? = null
        override fun getCount() = seasons.size
        override fun bindView(view: SeasonItemView) {
            val season = seasons[view.pos]
            view.setName(season.name())
        }
    }

    val seasonsListPresenter = SeasonsListPresenter()

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        viewState.init()
        loadData()

        seasonsListPresenter.itemClickListener = { view ->
            val season = seasonsListPresenter.seasons[view.pos]
            router.navigateTo(screens.season(season))
        }
    }

    private fun loadData() {
        teamsRepo.getSeasons()
            .observeOn(uiScheduler)
            .subscribe({ repos ->
                Log.d("SeasonsPresenter", repos.toString())
                seasonsListPresenter.seasons.clear()
                seasonsListPresenter.seasons.addAll(repos)
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