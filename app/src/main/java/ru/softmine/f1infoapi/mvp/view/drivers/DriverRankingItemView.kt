package ru.softmine.f1infoapi.mvp.view.drivers

import ru.softmine.f1infoapi.mvp.view.common.ItemView

interface DriverRankingItemView: ItemView {
    fun setPosition(position: Int)
    fun setDriverPhoto(imageUrl: String)
    fun setDriverName(name: String)
    fun setTeamLogo(imageUrl: String)
    fun setTeamName(name: String)
    fun setPoints(points: Int)
    fun setBehindPoints(points: Int)
}