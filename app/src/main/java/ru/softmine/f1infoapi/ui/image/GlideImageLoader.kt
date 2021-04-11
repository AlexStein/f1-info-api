package ru.softmine.f1infoapi.ui.image

import android.graphics.Bitmap
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import ru.softmine.f1infoapi.mvp.model.image.ImageLoader
import ru.softmine.f1infoapi.mvp.model.network.NetworkStatus
import ru.softmine.f1infoapi.mvp.model.repository.interfaces.ImageCache
import java.io.ByteArrayOutputStream


class GlideImageLoader(
    val imageCache: ImageCache,
    val networkStatus: NetworkStatus,
    val location: String
) :
    ImageLoader<ImageView> {

    override fun load(url: String, container: ImageView) {
        networkStatus.isOnlineSingle()
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe { isOnline ->
                if (isOnline) {
                    Glide.with(container.context)
                        .asBitmap()
                        .load(url)
                        .listener(object : RequestListener<Bitmap> {
                            override fun onLoadFailed(
                                e: GlideException?,
                                model: Any?,
                                target: Target<Bitmap>?,
                                isFirstResource: Boolean
                            ): Boolean {
                                return true
                            }

                            override fun onResourceReady(
                                resource: Bitmap?,
                                model: Any?,
                                target: Target<Bitmap>?,
                                dataSource: DataSource?,
                                isFirstResource: Boolean
                            ): Boolean {
                                val compressFormat =
                                    if (url.contains(".jpg")) Bitmap.CompressFormat.JPEG else Bitmap.CompressFormat.PNG
                                val stream = ByteArrayOutputStream()
                                resource?.compress(compressFormat, 100, stream)
                                val bytes = stream.use { it.toByteArray() }
                                imageCache.putImage(url, location, bytes)
                                return false
                            }
                        })
                        .into(container)
                } else {
                    imageCache.getImage(url).observeOn(AndroidSchedulers.mainThread()).subscribe({
                        Glide.with(container.context)
                            .load(it)
                            .into(container)
                    }, {

                    })
                }
            }
    }

}