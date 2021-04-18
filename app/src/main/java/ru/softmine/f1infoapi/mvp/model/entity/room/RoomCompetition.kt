package ru.softmine.f1infoapi.mvp.model.entity.room

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
class RoomCompetition(
    @PrimaryKey var id: Int,
    val name: String,
    val location_country: String,
    val location_city: String
)
