package com.o7.qareporter.presentation.mvp.presenter

import com.o7.qareporter.domain.interactor.ReportsInteractor
import com.o7.qareporter.presentation.base.Presenter
import com.o7.qareporter.presentation.mvp.view.MainView

class MainPresenter(view: MainView, private val reportsInteractor: ReportsInteractor) : Presenter<MainView>(view) {

    fun init() {
        view.init()
    }

    fun loadReports() {
        val reports = reportsInteractor.getReports()
        if (reports.isEmpty()) {
            view.showEmptyView()
        } else {
            view.updateReports(reports)
        }
    }
}
