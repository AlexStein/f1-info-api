package ru.softmine.f1infoapi.mvp.model.entity

import android.os.Parcelable
import com.google.gson.annotations.Expose
import kotlinx.parcelize.Parcelize

@Parcelize
data class Team(
    @Expose val id: String,
    @Expose val name: String,
    @Expose val logo: String,
    @Expose val president: String,
    @Expose val director: String,
    @Expose val technical_manager: String,
    @Expose val engine: String,
    @Expose val tyres: String,
) : Parcelable