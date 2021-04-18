package ru.softmine.f1infoapi.mvp.model.repository.interfaces

import io.reactivex.rxjava3.core.Single
import ru.softmine.f1infoapi.mvp.model.entity.Season

interface SeasonsRepository {
    fun getSeasons(): Single<List<Season>>
}