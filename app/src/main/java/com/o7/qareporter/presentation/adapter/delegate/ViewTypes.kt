package com.o7.qareporter.presentation.adapter.delegate

import com.o7.qareporter.domain.model.Report
import java.text.SimpleDateFormat
import java.util.*

interface ViewType

class ReportViewType(report: Report) : ViewType {
    companion object {
        private val dateFormat = SimpleDateFormat("dd MMMM yyyy hh:mm aaa", Locale.getDefault())
        const val TYPE = 0
    }

    val imageUri = report.imageUri
    val description = report.description
    val date: String = dateFormat.format(Date(report.dateInMillis))
}