package ru.softmine.f1infoapi.mvp.model.entity.room.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import ru.softmine.f1infoapi.mvp.model.entity.room.RoomDriver

@Dao
interface DriverDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(circuit: RoomDriver)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(vararg circuits: RoomDriver)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(circuits: List<RoomDriver>)

    @Update
    fun update(circuit: RoomDriver)

    @Update
    fun update(vararg circuits: RoomDriver)

    @Update
    fun update(circuits: List<RoomDriver>)

    @Query("SELECT * FROM RoomDriver")
    fun getAll(): List<RoomDriver>

    @Query("SELECT * FROM RoomDriver WHERE id = :id")
    fun findById(id: Int): RoomDriver?
}
