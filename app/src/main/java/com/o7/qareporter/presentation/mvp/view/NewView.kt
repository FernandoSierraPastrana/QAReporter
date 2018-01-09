package com.o7.qareporter.presentation.mvp.view

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Build
import android.support.design.widget.Snackbar
import android.support.v4.app.ActivityCompat
import android.support.v4.content.ContextCompat
import android.widget.EditText
import android.widget.ImageView
import android.widget.ScrollView
import com.o7.qareporter.R
import com.o7.qareporter.domain.model.Report
import com.o7.qareporter.getShareableUri
import com.o7.qareporter.presentation.mvp.activity.NewActivity
import java.lang.ref.WeakReference


class NewView(activity: NewActivity) {
    companion object {
        private const val IMAGE_MIME_TYPE = "image/*"
        private const val EMAIL_MIME_TYPE = "vnd.android.cursor.dir/email"
        private const val EMAIL_SUBJECT = "New Report"
        private const val EMAIL_CHOOSER = "Send email"
    }

    private val activityRef = WeakReference(activity)
    private lateinit var imageScreenshot: ImageView
    private lateinit var editDescription: EditText
    private lateinit var scrollView: ScrollView
    private lateinit var imageUri: Uri

    fun init() {
        val activity = activityRef.get()
        if (activity != null) {
            imageScreenshot = activity.findViewById(R.id.image_new_preview)
            editDescription = activity.findViewById(R.id.edit_new_description)
            scrollView = activity.findViewById(R.id.scroll_new)
        }
    }

    fun openImageChooser() {
        checkPermissions(NewActivity.REQUEST_CODE_CHOOSER, {
            val intent = Intent(Intent.ACTION_PICK)
            intent.type = IMAGE_MIME_TYPE
            activityRef.get()?.startActivityForResult(intent, NewActivity.REQUEST_CODE_CHOOSER)
        })
    }

    private fun checkPermissions(requestCode: Int, callback: () -> Unit) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            val activity = activityRef.get()
            if (activity != null) {
                val permissionCheck = ContextCompat.checkSelfPermission(activity, Manifest.permission.READ_EXTERNAL_STORAGE)
                if (PackageManager.PERMISSION_GRANTED == permissionCheck) {
                    callback.invoke()
                } else {
                    ActivityCompat.requestPermissions(activity, arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE), requestCode)
                }
            }
        } else {
            callback.invoke()
        }
    }

    fun updateImage(uri: Uri?) {
        checkPermissions(NewActivity.REQUEST_CODE_SHARE, {
            if (uri != null) {
                imageUri = uri
                imageScreenshot.setImageURI(imageUri)
            }
        })
    }

    fun sendReport(deviceInfo: String) {
        val activity = activityRef.get()
        if (activity != null) {
            val emailIntent = Intent(Intent.ACTION_SEND)
            emailIntent.type = EMAIL_MIME_TYPE
            emailIntent.putExtra(Intent.EXTRA_STREAM, imageUri.getShareableUri(activity))
            emailIntent.putExtra(Intent.EXTRA_SUBJECT, EMAIL_SUBJECT)
            emailIntent.putExtra(Intent.EXTRA_TEXT, buildEmailBody(deviceInfo))
            activity.startActivity(Intent.createChooser(emailIntent, EMAIL_CHOOSER))
            activity.finish()
        }
    }

    private fun buildEmailBody(deviceInfo: String): String {
        val builder = StringBuilder()
        val description = editDescription.text.toString()
        if (description.isNotEmpty()) {
            builder.append(description)
                    .appendln()
                    .appendln()
        }
        builder.append(deviceInfo)
        return builder.toString()
    }

    fun getReport(): Report = Report(imageUri.getShareableUri(activityRef.get()), editDescription.text.toString(), System.currentTimeMillis())

    fun isValid(): Boolean = ::imageUri.isInitialized

    fun showError() {
        Snackbar.make(scrollView, R.string.error_new, Snackbar.LENGTH_SHORT).show()
    }
}