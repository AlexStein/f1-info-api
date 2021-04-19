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
    fun insert(season: RoomSeason)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(vararg seasons: RoomSeason)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(seasons: List<RoomSeason>)

    @Update
    fun update(season: RoomSeason)

    @Update
    fun update(vararg seasons: RoomSeason)

    @Update
    fun update(seasons: List<RoomSeason>)

    @Query("SELECT * FROM RoomSeason")
    fun getAll(): List<RoomSeason>

    @Query("SELECT * FROM RoomSeason WHERE id = :id")
    fun findById(id: Int): RoomSeason?
}
