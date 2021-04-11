package ru.softmine.f1infoapi.mvp.model.entity

import android.os.Parcelable
import com.google.gson.annotations.Expose
import kotlinx.parcelize.Parcelize
import java.util.*

@Parcelize
data class Race(
    @Expose val id: Int,
    @Expose val circuit: Circuit,
    @Expose val competition: Competition,
    @Expose val date: Date,
    @Expose val distance: String,
    @Expose val laps: Int,
    @Expose val season: Int,
    @Expose val status: String,
    @Expose val type: String,
    @Expose val weather: String
) : Parcelable