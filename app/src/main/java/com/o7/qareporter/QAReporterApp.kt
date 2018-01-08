package com.o7.qareporter

import android.app.Application
import timber.log.Timber

class QAReporterApp : Application() {

    override fun onCreate() {
        super.onCreate()
        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }
    }
}