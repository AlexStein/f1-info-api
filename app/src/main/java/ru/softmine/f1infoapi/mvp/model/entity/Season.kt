package ru.softmine.f1infoapi.mvp.model.entity

import android.os.Parcelable
import com.google.gson.annotations.Expose
import kotlinx.parcelize.Parcelize

@Parcelize
data class Season(
    @Expose val id: Int
) : Parcelable {
    fun name() = id.toString()
}