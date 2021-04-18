package ru.softmine.f1infoapi.mvp.presenter

import com.github.terrakok.cicerone.Router
import moxy.MvpPresenter
import ru.softmine.f1infoapi.mvp.model.entity.Team
import ru.softmine.f1infoapi.mvp.navigation.IScreens
import ru.softmine.f1infoapi.mvp.view.TeamView
import javax.inject.Inject

class TeamPresenter(private val team: Team) : MvpPresenter<TeamView>() {

    @Inject lateinit var router: Router
    @Inject lateinit var screens: IScreens

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        viewState.init()

        viewState.setName(team.name)
        viewState.setLogo(team.logo)
        viewState.setPresident(team.president)
        viewState.setDirector(team.director)
        viewState.setTechnicalManager(team.technical_manager)
        viewState.setEngine(team.engine)
        viewState.setTyres(team.tyres)
    }


    fun backClick(): Boolean {
        router.exit()
        return true
    }
}