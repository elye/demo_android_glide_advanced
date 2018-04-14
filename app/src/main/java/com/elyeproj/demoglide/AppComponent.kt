package com.elyeproj.demoglide

import android.app.Application
import dagger.Component
import okhttp3.OkHttpClient
import javax.inject.Singleton


@Singleton
@Component(modules = [(AppModule::class)])
interface AppComponent {
    fun inject(app: Application)
    fun getPerformanceChecker() : PerformanceChecker
    fun getOkHttpClient() : OkHttpClient
}
