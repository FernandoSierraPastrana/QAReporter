package com.o7.qareporter.domain.interactor

import com.o7.qareporter.data.repository.ReportRepository
import com.o7.qareporter.domain.model.Report

class ReportsInteractor(private val reportRepository: ReportRepository) {

    fun saveReport(report: Report) {
        reportRepository.save(report)
    }
}