package ru.softmine.f1infoapi.mvp.model.entity.room

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
class RoomDriver(
    @PrimaryKey var id: Int,
    val name: String,
    val image: String,
    val nationality: String,
    val birthdate: String
)
