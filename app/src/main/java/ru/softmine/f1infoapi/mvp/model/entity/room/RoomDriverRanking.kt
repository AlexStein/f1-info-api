package ru.softmine.f1infoapi.mvp.model.entity.room

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(
    foreignKeys = [
        ForeignKey(
            entity = RoomDriver::class,
            parentColumns = ["id"],
            childColumns = ["driverId"],
            onDelete = ForeignKey.NO_ACTION
        ),
        ForeignKey(
            entity = RoomTeam::class,
            parentColumns = ["id"],
            childColumns = ["teamId"],
            onDelete = ForeignKey.NO_ACTION
        ),
        ForeignKey(
            entity = RoomSeason::class,
            parentColumns = ["id"],
            childColumns = ["seasonId"],
            onDelete = ForeignKey.NO_ACTION
        )
    ],
    indices = [
        Index(value = ["driverId", "teamId", "seasonId"], unique = true),
        Index(value = ["driverId"]),
        Index(value = ["teamId"]),
        Index(value = ["seasonId"]),
    ]
)
class RoomDriverRanking(
    var driverId: Int,
    var teamId: Int,
    var position: Int,
    var points: Int,
    var wins: Int,
    var behind: Int,
    var seasonId: Int,
){
    @PrimaryKey var id: Int = 0
}
