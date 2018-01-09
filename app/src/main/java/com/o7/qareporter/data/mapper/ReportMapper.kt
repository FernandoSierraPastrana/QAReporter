package com.o7.qareporter.data.mapper

import android.net.Uri
import com.o7.qareporter.data.model.RReport
import com.o7.qareporter.domain.model.Report

class ReportMapper : Mapper<Report, RReport> {

    override fun transform(input: Report): RReport = RReport(input.imageUri.toString(), input.description, input.dateInMillis)

    override fun transform(input: RReport): Report = Report(Uri.parse(input.imageUri), input.description, input.dateInMillis)
}