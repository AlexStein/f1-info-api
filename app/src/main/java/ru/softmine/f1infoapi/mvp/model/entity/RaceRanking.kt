package ru.softmine.f1infoapi.mvp.model.entity

import android.os.Parcelable
import com.google.gson.annotations.Expose
import kotlinx.parcelize.Parcelize

@Parcelize
data class RaceRanking(
    @Expose val position: Int,
    @Expose val race: Race,
    @Expose val driver: Driver,
    @Expose val team: Team,
    @Expose val time: String,
    @Expose val laps: Int,
    @Expose val grid: String,
    @Expose val pits: Int
) : Parcelable