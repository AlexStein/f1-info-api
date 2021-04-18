package ru.softmine.f1infoapi.mvp.presenter

import android.util.Log
import com.github.terrakok.cicerone.Router
import io.reactivex.rxjava3.core.Scheduler
import moxy.InjectViewState
import moxy.MvpPresenter
import ru.softmine.f1infoapi.mvp.model.entity.Team
import ru.softmine.f1infoapi.mvp.model.repository.interfaces.TeamsRepository
import ru.softmine.f1infoapi.mvp.navigation.IScreens
import ru.softmine.f1infoapi.mvp.presenter.interfaces.ListPresenter
import ru.softmine.f1infoapi.mvp.view.CircuitsView
import ru.softmine.f1infoapi.mvp.view.TeamItemView
import ru.softmine.f1infoapi.mvp.view.TeamsView
import javax.inject.Inject
import javax.inject.Named

@InjectViewState
class TeamsPresenter: MvpPresenter<TeamsView>() {

    @field:Named("ui") @Inject lateinit var uiScheduler: Scheduler
    @Inject lateinit var teamsRepo: TeamsRepository
    @Inject lateinit var screens: IScreens
    @Inject lateinit var router: Router

    inner class TeamsListPresenter : ListPresenter<TeamItemView> {
        val teams = mutableListOf<Team>()

        override var itemClickListener: ((TeamItemView) -> Unit)? = null
        override fun getCount() = teams.size
        override fun bindView(view: TeamItemView) {
            val team = teams[view.pos]
            view.setName(team.name)
            view.setLogo(team.logo)
        }
    }

    val teamsListPresenter = TeamsListPresenter()

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        viewState.init()
        loadData()

        teamsListPresenter.itemClickListener = { view ->
            val team = teamsListPresenter.teams[view.pos]
            router.navigateTo(screens.team(team))
        }
    }

    private fun loadData() {
        teamsRepo.getTeams()
            .observeOn(uiScheduler)
            .subscribe({ repos ->
                Log.d("TeamsPresenter", repos.toString())
                teamsListPresenter.teams.clear()
                teamsListPresenter.teams.addAll(repos)
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