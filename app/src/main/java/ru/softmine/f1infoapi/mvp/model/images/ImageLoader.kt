package ru.softmine.f1infoapi.mvp.model.images

interface ImageLoader<T> {
    fun load(url: String, container: T)
}