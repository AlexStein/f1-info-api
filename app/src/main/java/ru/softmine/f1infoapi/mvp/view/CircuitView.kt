package ru.softmine.f1infoapi.mvp.view

import moxy.MvpView
import moxy.viewstate.strategy.AddToEndSingleStrategy
import moxy.viewstate.strategy.StateStrategyType

@StateStrategyType(AddToEndSingleStrategy::class)
interface CircuitView: MvpView {
    fun init()
    fun setName(text: String)
    fun loadImage(imageUrl: String)
    fun setCountry(text: String)
    fun setCity(text: String)
    fun setLength(len: String)
    fun setOpenDate(year: Int)
    fun setCapacity(capacity: Int)
    fun setOwner(text: String?)
}
