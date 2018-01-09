package com.o7.qareporter.domain.model

import android.net.Uri

data class Report(val imageUri: Uri, val description: String?, val dateInMillis: Long)
