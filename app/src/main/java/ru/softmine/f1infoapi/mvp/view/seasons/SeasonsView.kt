package ru.softmine.f1infoapi.mvp.view.seasons

import moxy.MvpView
import moxy.viewstate.strategy.AddToEndSingleStrategy
import moxy.viewstate.strategy.StateStrategyType

@StateStrategyType(AddToEndSingleStrategy::class)
interface SeasonsView : MvpView {
    fun init()
    fun updateList()
}