package ru.softmine.f1infoapi.mvp.model.entity.room.dao

import androidx.room.*
import ru.softmine.f1infoapi.mvp.model.entity.room.RoomDriverRanking

@Dao
interface DriverRankingDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(driverRanking: RoomDriverRanking)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(vararg driverRankings: RoomDriverRanking)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(driverRankings: List<RoomDriverRanking>)

    @Update
    fun update(driverRanking: RoomDriverRanking)

    @Update
    fun update(vararg driverRankings: RoomDriverRanking)

    @Update
    fun update(driverRankings: List<RoomDriverRanking>)

    @Query("SELECT * FROM RoomDriverRanking WHERE seasonId = :seasonId")
    fun getSeasonRanking(seasonId: Int): List<RoomDriverRanking>

    @Query("SELECT * FROM RoomDriverRanking WHERE driverId = :driverId")
    fun getDriverRanking(driverId: Int): List<RoomDriverRanking>
}
