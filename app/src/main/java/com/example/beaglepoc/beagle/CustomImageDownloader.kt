package com.example.beaglepoc.beagle

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.drawable.BitmapDrawable
import android.provider.ContactsContract.CommonDataKinds.Website.URL
import android.provider.SyncStateContract
import android.util.Log
import android.util.LruCache
import android.widget.ImageView
import androidx.lifecycle.lifecycleScope
import br.com.zup.beagle.android.annotation.BeagleComponent
import br.com.zup.beagle.android.imagedownloader.BeagleImageDownloader
import br.com.zup.beagle.android.widget.RootView
import com.example.beaglepoc.const.Constants
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.io.InputStream
import java.net.URL

@BeagleComponent
class CustomImageDownloader: BeagleImageDownloader {

    private val imageDownloader: ImageDownloader = ImageDownloader()

    override fun download(url: String, imageView: ImageView, rootView: RootView) {

        imageView.post {
            rootView.getLifecycleOwner().lifecycleScope.launch(Dispatchers.IO) {
                val bitmap = try {
                    imageDownloader.getRemoteImage(Constants.BASE_URL + url, imageView.width, imageView.height)
                } catch (e: Exception) {
                    Log.e("LOG_TAG", e.message ?: "Error when try to download Image")
                    null
                }

                bitmap?.let {
                    setImage(imageView, bitmap)
                }
            }
        }
    }

    private suspend fun setImage(view: ImageView, bitmap: Bitmap?) {
        withContext(Dispatchers.Main) {
            view.context?.let {
                view.setImageDrawable(BitmapDrawable(it.resources, bitmap))
            }
        }
    }

    internal class ImageDownloader {

        suspend fun getRemoteImage(url: String, contentWidth: Int, contentHeight: Int) : Bitmap? {
            val cacheId = LruImageCache.generateBitmapId(url, contentWidth, contentHeight)

            return withContext(Dispatchers.IO) {
                val bitmapCached = LruImageCache.get(cacheId)

                bitmapCached
                    ?: url.let {
                        downloadBitmap(it, contentWidth, contentHeight).apply {
                            LruImageCache.put(cacheId, this)
                        }
                    }
            }
        }

        private fun downloadBitmap(url: String?, contentWidth: Int, contentHeight: Int) : Bitmap {
            val inputStream: InputStream = URL(url).openStream()
            return BitmapFactory.decodeStream(inputStream)
        }
    }

    internal object LruImageCache {

        private val cache: LruCache<String, Bitmap> by lazy {
            LruCache<String, Bitmap>(anEighthOfMemory())
        }

        fun put(key: String, bitmap: Bitmap?) {
            if (bitmap != null) {
                cache.put(key, bitmap)
            }
        }

        fun get(key: String?): Bitmap? = cache.get(key)

        private fun anEighthOfMemory() = ((Runtime.getRuntime().maxMemory() / 1024).toInt() / 8)

        fun generateBitmapId(
            url: String?,
            contentWidth: Int,
            contentHeight: Int
        ) = StringBuilder().append(url).append(contentWidth).append(contentHeight).toString()
    }
}