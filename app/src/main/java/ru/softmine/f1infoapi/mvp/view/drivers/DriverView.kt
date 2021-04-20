package ru.softmine.f1infoapi.mvp.view.drivers

import moxy.MvpView
import moxy.viewstate.strategy.AddToEndSingleStrategy
import moxy.viewstate.strategy.StateStrategyType

@StateStrategyType(AddToEndSingleStrategy::class)
interface DriverView: MvpView {
    fun init()
    fun setName(text: String)
    fun loadImage(imageUrl: String)
    fun setNationality(text: String?)
    fun setBirthdate(text: String?)
}
