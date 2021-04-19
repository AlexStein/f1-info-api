package ru.softmine.f1infoapi.mvp.model.entity.room.dao

import androidx.room.*
import ru.softmine.f1infoapi.mvp.model.entity.room.RoomTeamRanking

@Dao
interface TeamRankingDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(teamRanking: RoomTeamRanking)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(vararg teamRanking: RoomTeamRanking)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(teamRanking: List<RoomTeamRanking>)

    @Update
    fun update(teamRanking: RoomTeamRanking)

    @Update
    fun update(vararg teamRankings: RoomTeamRanking)

    @Update
    fun update(teamRankings: List<RoomTeamRanking>)

    @Query("SELECT * FROM RoomTeamRanking WHERE seasonId = :seasonId")
    fun getSeasonRanking(seasonId: Int): List<RoomTeamRanking>

    @Query("SELECT * FROM RoomTeamRanking WHERE teamId = :teamId")
    fun getTeamRanking(teamId: Int): List<RoomTeamRanking>
}
