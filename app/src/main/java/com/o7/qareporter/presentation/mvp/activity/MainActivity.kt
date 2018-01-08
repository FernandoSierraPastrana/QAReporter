package com.o7.qareporter.presentation.mvp.activity

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.o7.qareporter.R
import com.o7.qareporter.presentation.mvp.presenter.MainPresenter
import com.o7.qareporter.presentation.mvp.view.MainView

class MainActivity : AppCompatActivity() {
    private lateinit var presenter: MainPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        presenter = MainPresenter(MainView(this))
        presenter.init()
    }
}
