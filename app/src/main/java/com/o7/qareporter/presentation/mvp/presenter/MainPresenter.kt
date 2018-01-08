package com.o7.qareporter.presentation.mvp.presenter

import com.o7.qareporter.presentation.base.Presenter
import com.o7.qareporter.presentation.mvp.view.MainView

class MainPresenter(view: MainView) : Presenter<MainView>(view) {

    fun init() {
        view.init()
    }
}
