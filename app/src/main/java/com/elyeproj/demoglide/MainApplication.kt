package com.elyeproj.demoglide

import android.app.Application

class MainApplication : Application() {

    companion object {
        lateinit var component: AppComponent
    }

    override fun onCreate() {
        super.onCreate()
        createComponent()
        component.inject(this)
    }

    private fun createComponent() {
        component = DaggerAppComponent
                .builder()
                .appModule(AppModule(this))
                .build()
    }
}
