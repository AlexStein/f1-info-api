package ru.softmine.f1infoapi.mvp.model.image

interface ImageLoader<T> {
    fun load(url: String, container: T)
}