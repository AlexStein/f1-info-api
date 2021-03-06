package ru.softmine.f1infoapi.mvp.model.repository.interfaces

import io.reactivex.rxjava3.core.Single
import ru.softmine.f1infoapi.mvp.model.entity.Circuit


interface CircuitsRepository {
    fun getCircuits(): Single<List<Circuit>>
    fun getCircuit(id: Int): Single<Circuit>
}