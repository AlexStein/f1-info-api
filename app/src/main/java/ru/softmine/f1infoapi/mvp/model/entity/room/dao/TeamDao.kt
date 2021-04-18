package ru.softmine.f1infoapi.mvp.model.entity.room.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import ru.softmine.f1infoapi.mvp.model.entity.room.RoomTeam

@Dao
interface TeamDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(team: RoomTeam)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(vararg circuits: RoomTeam)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(teams: List<RoomTeam>)

    @Update
    fun update(teams: RoomTeam)

    @Update
    fun update(vararg circuits: RoomTeam)

    @Update
    fun update(circuits: List<RoomTeam>)

    @Query("SELECT * FROM RoomTeam")
    fun getAll(): List<RoomTeam>

    @Query("SELECT * FROM RoomTeam WHERE id = :id")
    fun findById(id: Int): RoomTeam?
}
