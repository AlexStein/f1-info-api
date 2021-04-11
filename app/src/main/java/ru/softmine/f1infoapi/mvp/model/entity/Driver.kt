package ru.softmine.f1infoapi.mvp.model.entity

import android.os.Parcelable
import com.google.gson.annotations.Expose
import kotlinx.parcelize.Parcelize
import java.util.*

@Parcelize
data class Driver(
    @Expose val id: String,
    @Expose val name: String,
    @Expose val image: String,
    @Expose val Driver: String,
    @Expose val birthdate: Date
) : Parcelable