package ru.softmine.f1infoapi.mvp.model.entity

import android.os.Parcelable
import com.google.gson.annotations.Expose
import kotlinx.parcelize.Parcelize

@Parcelize
data class Circuit(
    @Expose val id: Int,
    @Expose val name: String,
    @Expose val image: String,
    @Expose val length: String,
    @Expose val opened: Int,
    @Expose val capacity: Int,
    @Expose val owner: String
) : Parcelable