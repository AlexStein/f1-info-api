package ru.softmine.f1infoapi.mvp.model.repository.interfaces.cache

import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single

interface ImageCache {
    fun putImage(url: String, location: String, byteArray: ByteArray): Completable
    fun getImage(url: String): Single<ByteArray?>
}
