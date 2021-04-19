package ru.softmine.f1infoapi.mvp.model.entity.room.dao

import androidx.room.*
import ru.softmine.f1infoapi.mvp.model.entity.room.RoomTeam

@Dao
interface TeamDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(team: RoomTeam)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(vararg teams: RoomTeam)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(teams: List<RoomTeam>)

    @Update
    fun update(teams: RoomTeam)

    @Update
    fun update(vararg teams: RoomTeam)

    @Update
    fun update(teams: List<RoomTeam>)

    @Query("SELECT * FROM RoomTeam")
    fun getAll(): List<RoomTeam>

    @Query("SELECT * FROM RoomTeam WHERE id = :id")
    fun findById(id: Int): RoomTeam?
}
