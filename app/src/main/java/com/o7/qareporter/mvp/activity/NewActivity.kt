package com.o7.qareporter.mvp.activity

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.o7.qareporter.R
import com.o7.qareporter.getDeviceInfo
import com.o7.qareporter.mvp.presenter.NewPresenter
import com.o7.qareporter.mvp.view.NewView
import timber.log.Timber

class NewActivity : AppCompatActivity() {
    private lateinit var presenter: NewPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_new)
        supportActionBar?.setHomeAsUpIndicator(R.drawable.ic_close)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        presenter = NewPresenter(NewView(this))
        Timber.d(getDeviceInfo())
    }
}
