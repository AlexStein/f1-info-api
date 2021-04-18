package ru.softmine.f1infoapi.mvp.model.entity

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.Expose

data class ResponseObject<T> constructor(
    @Expose val results: Int,
    @Expose val response: List<T>
) : Parcelable {

    constructor(parcel: Parcel) : this(
        parcel.readInt(),
        mutableListOf<T>().also { list ->
            parcel.readList(list, ResponseObject<T>::response.javaClass.classLoader)
        }
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(results)
        parcel.writeList(response)
    }

    override fun describeContents(): Int = 0

    companion object CREATOR : Parcelable.Creator<ResponseObject<Parcelable>> {
        override fun createFromParcel(parcel: Parcel): ResponseObject<Parcelable> {
            return ResponseObject(parcel)
        }

        override fun newArray(size: Int): Array<ResponseObject<Parcelable>?> {
            return arrayOfNulls(size)
        }
    }


}