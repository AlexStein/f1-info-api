package ru.softmine.f1infoapi.mvp.view.seasons

import moxy.MvpView
import moxy.viewstate.strategy.AddToEndSingleStrategy
import moxy.viewstate.strategy.StateStrategyType

@StateStrategyType(AddToEndSingleStrategy::class)
interface SeasonView: MvpView {
    fun init()
    fun setSeasonName(season: String)
}