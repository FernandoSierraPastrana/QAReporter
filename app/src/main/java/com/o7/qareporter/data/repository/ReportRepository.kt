package com.o7.qareporter.data.repository

import com.o7.qareporter.data.mapper.ReportMapper
import com.o7.qareporter.data.model.RReport
import com.o7.qareporter.data.source.RReportDataSource
import com.o7.qareporter.domain.model.Report

class ReportRepository(dataSource: RReportDataSource, mapper: ReportMapper) : Repository<Report, RReport>(dataSource, mapper)
