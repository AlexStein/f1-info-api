package ru.softmine.f1infoapi.mvp.model.repository

import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers
import ru.softmine.f1infoapi.mvp.model.api.DataSource
import ru.softmine.f1infoapi.mvp.model.entity.Season
import ru.softmine.f1infoapi.mvp.model.network.NetworkStatus
import ru.softmine.f1infoapi.mvp.model.repository.interfaces.SeasonsCache
import ru.softmine.f1infoapi.mvp.model.repository.interfaces.SeasonsRepository

class RetrofitSeasonsRepository (
    private val api: DataSource,
    private val networkStatus: NetworkStatus,
    private val seasonsCache: SeasonsCache
    ) : SeasonsRepository {

        override fun getSeasons(): Single<List<Season>> =
            networkStatus.isOnlineSingle().flatMap { isOnline ->
                if (isOnline) {
                    api.getSeasons().flatMap { result ->
                        val seasons = result.response.map { parcelable ->
                            Season(parcelable)
                        }
                        seasonsCache.putSeasons(seasons).andThen(Single.just(seasons))
                    }
                } else {
                    seasonsCache.getSeasons()
                }
            }.subscribeOn(Schedulers.io())
}

