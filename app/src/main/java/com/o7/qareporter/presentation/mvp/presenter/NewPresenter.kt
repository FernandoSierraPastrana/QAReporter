package com.o7.qareporter.presentation.mvp.presenter

import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import com.o7.qareporter.domain.interactor.ReportsInteractor
import com.o7.qareporter.presentation.base.Presenter
import com.o7.qareporter.presentation.mvp.activity.NewActivity
import com.o7.qareporter.presentation.mvp.view.NewView


class NewPresenter(view: NewView, private val reportsInteractor: ReportsInteractor) : Presenter<NewView>(view) {
    private lateinit var shareIntent: Intent

    fun init() {
        view.init()
    }

    fun chooseNewImage() {
        view.openImageChooser()
    }

    fun interceptOnActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (NewActivity.REQUEST_CODE_CHOOSER == requestCode && Activity.RESULT_OK == resultCode) {
            view.updateImage(data?.data)
        }
    }

    fun interceptOnRequestPermissionResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        var hasPermissionsGranted = true
        grantResults.map {
            if (PackageManager.PERMISSION_DENIED == it) {
                hasPermissionsGranted = false
            }
        }
        if (hasPermissionsGranted) {
            when (requestCode) {
                NewActivity.REQUEST_CODE_CHOOSER -> chooseNewImage()
                NewActivity.REQUEST_CODE_SHARE -> getImageFromIntent(shareIntent)
            }
        }
    }

    fun getImageFromIntent(intent: Intent) {
        shareIntent = intent
        if (intent.clipData != null && intent.clipData.itemCount > 0) {
            view.updateImage(intent.clipData.getItemAt(0).uri)
        }
    }

    fun saveAndSendReport(deviceInfo: String) {
        if (view.isValid()) {
            reportsInteractor.saveReport(view.getReport())
            view.sendReport(deviceInfo)
        } else {
            view.showError()
        }
    }
}