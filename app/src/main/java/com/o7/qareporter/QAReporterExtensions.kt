package com.o7.qareporter

import android.app.Activity
import android.util.DisplayMetrics

fun Activity.getDeviceInfo(): String {
    val stringBuilder = StringBuilder()
    stringBuilder.append(getString(R.string.device_details))
    stringBuilder.append(getString(R.string.device_manufacturer).format(android.os.Build.MANUFACTURER))
    stringBuilder.append(getString(R.string.device_model).format(android.os.Build.MODEL))
    stringBuilder.append(getString(R.string.device_screen_inches).format(getScreenSizeInInches()))
    stringBuilder.append(getString(R.string.device_screen_px).format(getScreenResolutionInPx()))
    stringBuilder.append(getString(R.string.device_screen_dp).format(getScreenResolutionInDp()))
    stringBuilder.append(getString(R.string.device_screen_density).format(getScreenDensity()))
    return stringBuilder.toString()
}

private fun Activity.getScreenSizeInInches(): Double {
    val displayMetrics = DisplayMetrics()
    windowManager.defaultDisplay.getMetrics(displayMetrics)
    val width = displayMetrics.widthPixels
    val height = displayMetrics.heightPixels
    val wi = width.toDouble() / displayMetrics.xdpi.toDouble()
    val hi = height.toDouble() / displayMetrics.ydpi.toDouble()
    return Math.sqrt(Math.pow(wi, 2.0) + Math.pow(hi, 2.0))
}

private fun Activity.getScreenResolutionInPx(): String {
    val displayMetrics = DisplayMetrics()
    windowManager.defaultDisplay.getMetrics(displayMetrics)
    return getString(R.string.resolution_template).format(displayMetrics.widthPixels, displayMetrics.heightPixels)
}

private fun Activity.getScreenResolutionInDp(): String {
    val displayMetrics = DisplayMetrics()
    windowManager.defaultDisplay.getMetrics(displayMetrics)
    val dpWidth = displayMetrics.widthPixels / displayMetrics.density
    val dpHeight = displayMetrics.heightPixels / displayMetrics.density
    return getString(R.string.resolution_template_float).format(dpWidth, dpHeight)
}

private fun Activity.getScreenDensity(): String {
    val displayMetrics = DisplayMetrics()
    windowManager.defaultDisplay.getMetrics(displayMetrics)
    val order = when {
        displayMetrics.densityDpi in 0..DisplayMetrics.DENSITY_LOW -> "ldpi"
        displayMetrics.densityDpi in 0..DisplayMetrics.DENSITY_MEDIUM -> "mdpi"
        displayMetrics.densityDpi in 0..DisplayMetrics.DENSITY_HIGH -> "hdpi"
        displayMetrics.densityDpi in 0..DisplayMetrics.DENSITY_XHIGH -> "xhdpi"
        displayMetrics.densityDpi in 0..DisplayMetrics.DENSITY_XXHIGH -> "xxhdpi"
        displayMetrics.densityDpi in 0..DisplayMetrics.DENSITY_XXXHIGH -> "xxxhdpi"
        else -> "tvdpi"
    }
    return getString(R.string.screen_density_template).format(displayMetrics.densityDpi, order)
}
