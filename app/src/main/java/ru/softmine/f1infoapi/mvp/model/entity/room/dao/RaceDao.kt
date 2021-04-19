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
    fun insert(race: RoomRace)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(vararg races: RoomRace)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(races: List<RoomRace>)

    @Update
    fun update(race: RoomRace)

    @Update
    fun update(vararg races: RoomRace)

    @Update
    fun update(races: List<RoomRace>)

    @Query("SELECT * FROM RoomRace")
    fun getAll(): List<RoomRace>

    @Query("SELECT * FROM RoomRace WHERE id = :id")
    fun findById(id: Int): RoomRace?
}
