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
    fun insert(driver: RoomDriver)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(vararg drivers: RoomDriver)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(drivers: List<RoomDriver>)

    @Update
    fun update(driver: RoomDriver)

    @Update
    fun update(vararg drivers: RoomDriver)

    @Update
    fun update(drivers: List<RoomDriver>)

    @Query("SELECT * FROM RoomDriver")
    fun getAll(): List<RoomDriver>

    @Query("SELECT * FROM RoomDriver WHERE id = :id")
    fun findById(id: Int): RoomDriver?
}
