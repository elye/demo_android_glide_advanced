package com.elyeproj.demoglide

import android.content.Context
import com.bumptech.glide.Glide
import com.bumptech.glide.GlideBuilder
import com.bumptech.glide.MemoryCategory
import com.bumptech.glide.Registry
import com.bumptech.glide.annotation.GlideModule
import com.bumptech.glide.load.DecodeFormat
import com.bumptech.glide.module.AppGlideModule
import com.bumptech.glide.request.RequestOptions
import java.io.InputStream

@GlideModule
class MyImageGlideModule : AppGlideModule() {

    override fun registerComponents(context: Context, glide: Glide, registry: Registry) {
        super.registerComponents(context, glide, registry)
        val client = MainApplication.component.getOkHttpClient()
        val devicePerformance = MainApplication.component.getPerformanceChecker()

        if (devicePerformance.isHighPerformingDevice) {
            glide.setMemoryCategory(MemoryCategory.NORMAL)
        } else {
            glide.setMemoryCategory(MemoryCategory.LOW)
        }

        registry.append(MyImageModel::class.java, InputStream::class.java, MyImageStreamLoader.Factory(client))
    }

    override fun applyOptions(context: Context, builder: GlideBuilder) {
        val performanceDetect = MainApplication.component.getPerformanceChecker()
        if (performanceDetect.isHighPerformingDevice) {
            builder.setDefaultRequestOptions(RequestOptions().format(DecodeFormat.PREFER_ARGB_8888))
        } else {
            builder.setDefaultRequestOptions(RequestOptions().format(DecodeFormat.PREFER_RGB_565))
        }
    }

    override fun isManifestParsingEnabled(): Boolean {
        return false
    }
}
