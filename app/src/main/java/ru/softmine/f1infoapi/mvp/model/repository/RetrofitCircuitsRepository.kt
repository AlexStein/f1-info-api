package ru.softmine.f1infoapi.mvp.model.repository

import android.util.Log
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers
import ru.softmine.f1infoapi.mvp.model.api.DataSource
import ru.softmine.f1infoapi.mvp.model.entity.Circuit
import ru.softmine.f1infoapi.mvp.model.network.NetworkStatus
import ru.softmine.f1infoapi.mvp.model.repository.interfaces.CircuitsCache
import ru.softmine.f1infoapi.mvp.model.repository.interfaces.CircuitsRepository

class RetrofitCircuitsRepository (
    private val api: DataSource,
    private val networkStatus: NetworkStatus,
    private val circuitsCache: CircuitsCache
    ) : CircuitsRepository {

        override fun getCircuits(): Single<List<Circuit>> =
            networkStatus.isOnlineSingle().flatMap { isOnline ->
                if (isOnline) {
                    api.getCircuits().flatMap { result ->
                        Log.d("RetrofitCircuitsRepository", result.toString())
                        val circuits = result.response.map { parcelable ->
                            parcelable
                        }
                        circuitsCache.putCircuits(circuits).andThen(Single.just(circuits))
                    }
                } else {
                    circuitsCache.getCircuits()
                }
            }.subscribeOn(Schedulers.io())

    override fun getCircuit(id: Int): Single<Circuit> =
        networkStatus.isOnlineSingle().flatMap { isOnline ->
            if (isOnline) {
                api.getCircuit(id).flatMap { result ->
                    val circuit = result.response.let {
                        it[0]
                    }
                    circuitsCache.putCircuits(listOf(circuit)).andThen(Single.just(circuit))
                }
            } else {
                circuitsCache.getCircuit(id)
            }
        }.subscribeOn(Schedulers.io())
}

