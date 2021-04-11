package ru.softmine.f1infoapi.mvp.view

import moxy.MvpView
import moxy.viewstate.strategy.AddToEndSingleStrategy
import moxy.viewstate.strategy.StateStrategyType
import java.util.*

@StateStrategyType(AddToEndSingleStrategy::class)
interface CircuitView: MvpView {
    fun setName(text: String)
    fun loadImage(text: String)
    fun setLength(len: Int)
    fun setOpenDate(date: Date)
}
