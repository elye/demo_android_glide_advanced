package com.elyeproj.demoglide

import android.app.ActivityManager
import android.content.Context
import dagger.Module
import dagger.Provides
import okhttp3.Cache
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import javax.inject.Singleton


@Module
class AppModule(private val context: Context) {

    @Provides @Singleton fun provideContext() = context

    @Provides @Singleton fun provideActivityManager(context: Context): ActivityManager {
        return context.getSystemService(Context.ACTIVITY_SERVICE) as ActivityManager
    }

    @Provides @Singleton fun providesOkHttpClient(): OkHttpClient {
        val builder = OkHttpClient.Builder()
        if (BuildConfig.DEBUG) {
            val logging = HttpLoggingInterceptor()
            logging.level = HttpLoggingInterceptor.Level.HEADERS
            builder.addInterceptor(logging)
        }
        return builder.build()
    }

    @Provides @Singleton
    fun providesOkHttpCache(context: Context): Cache {
        val CACHE_SIZE = (5 * 1024 * 1024).toLong()
        return Cache(context.cacheDir, CACHE_SIZE)
    }
}
