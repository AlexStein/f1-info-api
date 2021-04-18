package ru.softmine.f1infoapi.mvp.model.entity.room.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import ru.softmine.f1infoapi.mvp.model.entity.room.RoomCircuit

@Dao
interface CircuitDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(circuit: RoomCircuit)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(vararg circuits: RoomCircuit)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(circuits: List<RoomCircuit>)

    @Update
    fun update(circuit: RoomCircuit)

    @Update
    fun update(vararg circuits: RoomCircuit)

    @Update
    fun update(circuits: List<RoomCircuit>)

    @Query("SELECT * FROM RoomCircuit")
    fun getAll(): List<RoomCircuit>

    @Query("SELECT * FROM RoomCircuit WHERE id = :id")
    fun findById(id: Int): RoomCircuit?
}
