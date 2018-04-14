package com.elyeproj.demoglide

import com.bumptech.glide.load.Options
import com.bumptech.glide.load.model.ModelLoader
import com.bumptech.glide.load.model.ModelLoaderFactory
import com.bumptech.glide.load.model.MultiModelLoaderFactory
import com.bumptech.glide.signature.ObjectKey
import okhttp3.OkHttpClient
import java.io.InputStream

class MyImageStreamLoader(private val client: OkHttpClient) : ModelLoader<MyImageModel, InputStream> {

    class Factory(private val client: OkHttpClient) : ModelLoaderFactory<MyImageModel, InputStream> {
        override fun build(multiFactory: MultiModelLoaderFactory): ModelLoader<MyImageModel, InputStream> {
            return MyImageStreamLoader(client)
        }

        override fun teardown() {
            // Do nothing, this instance doesn't own the client.
        }
    }

    override fun buildLoadData(displayImage: MyImageModel, width: Int, height: Int, options: Options)
            : ModelLoader.LoadData<InputStream>? {
        val url = displayImage.imageUrl
        return ModelLoader.LoadData(ObjectKey(url), MyImageStreamFetcher(client, url))
    }

    override fun handles(displayImage: MyImageModel): Boolean {
        return true
    }
}
