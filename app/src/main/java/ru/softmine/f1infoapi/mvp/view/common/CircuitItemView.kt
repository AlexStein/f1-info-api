package ru.softmine.f1infoapi.mvp.view.common

interface CircuitItemView: ItemView {
    fun setNumber(id: Int)
    fun setName(text: String)
    fun loadImage(imageUrl: String)
}