package ru.softmine.f1infoapi.mvp.model.repository.interfaces.cache

import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single
import ru.softmine.f1infoapi.mvp.model.entity.Season

interface SeasonsCache {
    fun putSeasons(seasons: List<Season>): Completable
    fun getSeasons(): Single<List<Season>>
}