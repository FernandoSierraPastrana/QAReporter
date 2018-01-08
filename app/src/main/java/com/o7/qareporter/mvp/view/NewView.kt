package com.o7.qareporter.mvp.view

import com.o7.qareporter.mvp.activity.NewActivity
import java.lang.ref.WeakReference

class NewView(activity: NewActivity) {
    private val activityRef = WeakReference(activity)

    fun init() {

    }
}