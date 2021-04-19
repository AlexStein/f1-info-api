package ru.softmine.f1infoapi.mvp.view

import ru.softmine.f1infoapi.mvp.view.common.ItemView

interface RaceRankingItemView: ItemView {
    fun setPosition(position: Int)
    fun setTrackMap(imageUrl: String)
    fun setTeamName(name: String)
    fun setDriverName(name: String)
    fun setPoints(points: Int)
}