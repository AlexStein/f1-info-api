package ru.softmine.f1infoapi.mvp.model.entity.room

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(
    foreignKeys = [
        ForeignKey(
            entity = RoomCircuit::class,
            parentColumns = ["id"],
            childColumns = ["circuitId"],
            onDelete = ForeignKey.CASCADE
        ),
        ForeignKey(
            entity = RoomCompetition::class,
            parentColumns = ["id"],
            childColumns = ["competitionId"],
            onDelete = ForeignKey.CASCADE
        )
    ],
    indices = [
        Index(value = ["circuitId"]),
        Index(value = ["competitionId"])
    ]
)
class RoomRace(
    @PrimaryKey var id: Int,
    val circuitId: Int,
    val competitionId: Int,
    val date: String,
    val distance: String,
    val laps: Int,
    val season: Int,
    val status: String,
    val type: String,
    val weather: String
)
