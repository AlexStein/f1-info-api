package ru.softmine.f1infoapi.mvp.view.teams

import moxy.MvpView
import moxy.viewstate.strategy.AddToEndSingleStrategy
import moxy.viewstate.strategy.StateStrategyType

@StateStrategyType(AddToEndSingleStrategy::class)
interface TeamsView : MvpView {
    fun init()
    fun updateList()
}