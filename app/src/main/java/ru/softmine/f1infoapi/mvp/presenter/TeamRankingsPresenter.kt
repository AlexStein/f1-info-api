package ru.softmine.f1infoapi.mvp.presenter

import com.github.terrakok.cicerone.Router
import io.reactivex.rxjava3.core.Scheduler
import moxy.InjectViewState
import moxy.MvpPresenter
import ru.softmine.f1infoapi.mvp.model.entity.Season
import ru.softmine.f1infoapi.mvp.model.entity.TeamRanking
import ru.softmine.f1infoapi.mvp.model.repository.interfaces.TeamsRepository
import ru.softmine.f1infoapi.mvp.navigation.IScreens
import ru.softmine.f1infoapi.mvp.presenter.interfaces.ListPresenter
import ru.softmine.f1infoapi.mvp.view.TeamRankingItemView
import ru.softmine.f1infoapi.mvp.view.TeamRankingsView
import javax.inject.Inject
import javax.inject.Named

@InjectViewState
class TeamRankingsPresenter(val season: Season): MvpPresenter<TeamRankingsView>() {

    @field:Named("ui") @Inject lateinit var uiScheduler: Scheduler
    @Inject lateinit var teamsRepo: TeamsRepository
    @Inject lateinit var screens: IScreens
    @Inject lateinit var router: Router

    inner class TeamsListPresenter : ListPresenter<TeamRankingItemView> {
        val teamRankings = mutableListOf<TeamRanking>()

        override var itemClickListener: ((TeamRankingItemView) -> Unit)? = null
        override fun getCount() = teamRankings.size
        override fun bindView(view: TeamRankingItemView) {
            val teamRanking = teamRankings[view.pos]
            view.setTeamName(teamRanking.team.name)
            view.setTeamLogo(teamRanking.team.logo)
            view.setPosition(teamRanking.position)
            view.setPoints(teamRanking.points)
        }
    }

    val teamsListPresenter = TeamsListPresenter()

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        viewState.init()
        loadData()

        teamsListPresenter.itemClickListener = { view ->
            val teamRanking = teamsListPresenter.teamRankings[view.pos]
            router.navigateTo(screens.team(teamRanking.team))
        }
    }

    private fun loadData() {
        teamsRepo.getTeamRankings(season)
            .observeOn(uiScheduler)
            .subscribe({ teamRankings ->
                teamsListPresenter.teamRankings.clear()
                teamsListPresenter.teamRankings.addAll(teamRankings)
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