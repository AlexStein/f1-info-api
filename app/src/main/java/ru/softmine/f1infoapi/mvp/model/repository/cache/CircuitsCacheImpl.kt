package ru.softmine.f1infoapi.mvp.model.repository.cache

import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single
import ru.softmine.f1infoapi.mvp.model.entity.Circuit
import ru.softmine.f1infoapi.mvp.model.entity.room.RoomCircuit
import ru.softmine.f1infoapi.mvp.model.entity.room.db.Database
import ru.softmine.f1infoapi.mvp.model.repository.interfaces.cache.CircuitsCache

class CircuitsCacheImpl(private val db: Database) : CircuitsCache {

    override fun putCircuits(circuits: List<Circuit>): Completable {
        return Completable.fromAction {
            Single.fromCallable {
                val roomCircuits = circuits.map { circuit ->
                    RoomCircuit(
                        circuit.id,
                        circuit.name,
                        circuit.image,
                        circuit.length,
                        circuit.opened,
                        circuit.capacity,
                        circuit.owner)
                }
                db.circuitDao.insert(roomCircuits)
                circuits
            }
        }
    }

    override fun getCircuits(): Single<List<Circuit>> {
        return Single.fromCallable {
            val circuits = db.circuitDao.getAll().map { roomCircuit ->
                Circuit(
                    roomCircuit.id,
                    roomCircuit.name,
                    roomCircuit.image,
                    roomCircuit.length,
                    roomCircuit.opened,
                    roomCircuit.capacity,
                    roomCircuit.owner)
            }
            circuits
        }
    }

    override fun getCircuit(id: Int): Single<Circuit> {
        return Single.fromCallable {
            db.circuitDao.findById(id)?.let { roomCircuit ->
                Circuit(
                    roomCircuit.id,
                    roomCircuit.name,
                    roomCircuit.image,
                    roomCircuit.length,
                    roomCircuit.opened,
                    roomCircuit.capacity,
                    roomCircuit.owner)
            }
        }
    }
}