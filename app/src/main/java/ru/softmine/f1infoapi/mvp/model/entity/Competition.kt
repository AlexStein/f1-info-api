package ru.softmine.f1infoapi.mvp.model.entity

import android.os.Parcelable
import com.google.gson.annotations.Expose
import kotlinx.parcelize.Parcelize

@Parcelize
data class Competition(
    @Expose val id: String,
    @Expose val name: String,
    @Expose val location: Location
) : Parcelable