package ru.softmine.f1infoapi.mvp.model.entity

import android.os.Parcelable
import com.google.gson.annotations.Expose
import kotlinx.parcelize.Parcelize

@Parcelize
data class DriverRanking(
    @Expose val position: Int,
    @Expose val driver: Driver,
    @Expose val team: Team,
    @Expose val points: Int,
    @Expose val wins: Int,
    @Expose val behind: Int,
    @Expose val season: Int
) : Parcelable