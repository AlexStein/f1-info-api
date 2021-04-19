package ru.softmine.f1infoapi.mvp.model.entity.room

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(
    foreignKeys = [
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
    indices = [Index(value = ["teamId", "seasonId"], unique = true)]
)
class RoomTeamRanking(
    var teamId: Int,
    var position: Int,
    var points: Int,
    var seasonId: Int,
) {
    @PrimaryKey var id: Int = 0
}
