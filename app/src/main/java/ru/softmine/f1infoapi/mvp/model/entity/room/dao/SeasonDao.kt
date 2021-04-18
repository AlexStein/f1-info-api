package ru.softmine.f1infoapi.mvp.model.entity.room.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import ru.softmine.f1infoapi.mvp.model.entity.room.RoomSeason

@Dao
interface SeasonDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(circuit: RoomSeason)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(vararg circuits: RoomSeason)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(circuits: List<RoomSeason>)

    @Update
    fun update(circuit: RoomSeason)

    @Update
    fun update(vararg circuits: RoomSeason)

    @Update
    fun update(circuits: List<RoomSeason>)

    @Query("SELECT * FROM RoomSeason")
    fun getAll(): List<RoomSeason>

    @Query("SELECT * FROM RoomSeason WHERE id = :id")
    fun findById(id: Int): RoomSeason?
}
