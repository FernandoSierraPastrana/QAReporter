package com.o7.qareporter

import android.app.Application
import io.realm.Realm
import timber.log.Timber

class QAReporterApp : Application() {

    override fun onCreate() {
        super.onCreate()
        Realm.init(this)
        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }
    }
}