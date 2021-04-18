package ru.softmine.f1infoapi.mvp.model.entity.room

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
class RoomCircuit(
    @PrimaryKey var id: Int,
    val name: String,
    val image: String,
    val length: String,
    val opened: Int,
    val capacity: Int,
    val owner: String?
)
