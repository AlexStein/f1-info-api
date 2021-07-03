package ru.softmine.f1infoapi.mvp.view.circuits

import moxy.MvpView
import moxy.viewstate.strategy.AddToEndSingleStrategy
import moxy.viewstate.strategy.StateStrategyType

@StateStrategyType(AddToEndSingleStrategy::class)
interface CircuitsView : MvpView {
    fun init()
    fun updateList()
}