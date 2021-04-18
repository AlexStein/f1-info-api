package ru.softmine.f1infoapi.mvp.model.entity.room.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import ru.softmine.f1infoapi.mvp.model.entity.room.RoomRace

@Dao
interface RaceDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(circuit: RoomRace)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(vararg circuits: RoomRace)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(circuits: List<RoomRace>)

    @Update
    fun update(circuit: RoomRace)

    @Update
    fun update(vararg circuits: RoomRace)

    @Update
    fun update(circuits: List<RoomRace>)

    @Query("SELECT * FROM RoomRace")
    fun getAll(): List<RoomRace>

    @Query("SELECT * FROM RoomRace WHERE id = :id")
    fun findById(id: Int): RoomRace?
}
