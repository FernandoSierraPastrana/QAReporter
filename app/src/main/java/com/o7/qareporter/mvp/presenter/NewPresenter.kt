package com.o7.qareporter.mvp.presenter

import com.o7.qareporter.base.Presenter
import com.o7.qareporter.mvp.view.NewView

class NewPresenter(view: NewView) : Presenter<NewView>(view) {

    fun init() {
        view.init()
    }
}