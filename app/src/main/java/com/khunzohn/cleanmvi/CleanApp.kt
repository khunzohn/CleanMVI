package com.khunzohn.cleanmvi

import android.app.Application
import com.airbnb.epoxy.Carousel
import com.khunzohn.cleanmvi.di.*
import org.koin.android.ext.android.startKoin

class CleanApp : Application() {

    override fun onCreate() {
        super.onCreate()

        Carousel.setDefaultGlobalSnapHelperFactory(null)

        startKoin(
            this, listOf(
                appModule, databaseModule,
                dataModule, interactorModule,
                mapperModule, presenterModule
            )
        )
    }
}