package com.o7.qareporter.mvp.presenter

import com.o7.qareporter.base.Presenter
import com.o7.qareporter.mvp.view.MainView

class MainPresenter(view: MainView) : Presenter<MainView>(view) {

    fun init() {
        view.init()
    }
}
