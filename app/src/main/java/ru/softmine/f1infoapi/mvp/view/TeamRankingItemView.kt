package ru.softmine.f1infoapi.mvp.view

import ru.softmine.f1infoapi.mvp.view.common.ItemView

interface TeamRankingItemView: ItemView {
    fun setPosition(position: Int)
    fun setTeamLogo(imageUrl: String)
    fun setTeamName(name: String)
    fun setPoints(points: Int)
}