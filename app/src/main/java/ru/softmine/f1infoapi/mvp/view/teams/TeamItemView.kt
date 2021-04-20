package ru.softmine.f1infoapi.mvp.view.teams

import ru.softmine.f1infoapi.mvp.view.common.ItemView

interface TeamItemView: ItemView {
    fun setLogo(imageUrl: String)
}