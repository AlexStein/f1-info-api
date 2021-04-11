package ru.softmine.f1infoapi.mvp.model.entity

import android.os.Parcelable
import com.google.gson.annotations.Expose
import kotlinx.parcelize.Parcelize

@Parcelize
data class TeamRanking(
    @Expose val position: Int,
    @Expose val team: Team,
    @Expose val points: Int,
    @Expose val season: Int
) : Parcelable