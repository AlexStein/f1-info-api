package ru.softmine.f1infoapi.mvp.model.entity.room

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
class RoomTeam(
    @PrimaryKey var id: Int,
    val name: String,
    val logo: String,
    val president: String,
    val director: String,
    val technical_manager: String,
    val engine: String,
    val tyres: String,
)
