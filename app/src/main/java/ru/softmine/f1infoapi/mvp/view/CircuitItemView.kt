package ru.softmine.f1infoapi.mvp.view

import ru.softmine.f1infoapi.mvp.view.common.ItemView

interface CircuitItemView: ItemView {
    fun setNumber(id: Int)
    fun loadImage(imageUrl: String)
}