package ru.softmine.f1infoapi.mvp.view

import moxy.MvpView
import moxy.viewstate.strategy.AddToEndSingleStrategy
import moxy.viewstate.strategy.StateStrategyType

@StateStrategyType(AddToEndSingleStrategy::class)
interface TeamView: MvpView {
    fun init()
    fun setName(text: String)
    fun setLogo(imageUrl: String)
    fun setDirector(text: String)
    fun setPresident(text: String)
    fun setTechnicalManager(text: String)
    fun setEngine(text: String)
    fun setTyres(text: String)
}
