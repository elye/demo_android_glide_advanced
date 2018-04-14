package com.elyeproj.demoglide

import android.graphics.drawable.Drawable
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
import com.bumptech.glide.request.transition.DrawableCrossFadeTransition


class MyImageRequestListener(private val callback: Callback? = null) : RequestListener<Drawable> {

    interface Callback {
        fun onFailure(message: String?)
        fun onSuccess(dataSource: String)
    }

    override fun onLoadFailed(e: GlideException?,
                              model: Any,
                              target: Target<Drawable>,
                              isFirstResource: Boolean): Boolean {

        callback?.onFailure(e?.message)
        return false
    }

    override fun onResourceReady(resource: Drawable,
                                 model: Any,
                                 target: Target<Drawable>,
                                 dataSource: DataSource,
                                 isFirstResource: Boolean): Boolean {

        callback?.onSuccess(dataSource.toString())
        target.onResourceReady(resource, DrawableCrossFadeTransition(1000, isFirstResource))
        return true

    }
}
