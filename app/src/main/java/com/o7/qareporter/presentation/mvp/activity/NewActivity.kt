package com.o7.qareporter.presentation.mvp.activity

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import com.o7.qareporter.R
import com.o7.qareporter.data.mapper.ReportMapper
import com.o7.qareporter.data.repository.ReportRepository
import com.o7.qareporter.data.source.RReportDataSource
import com.o7.qareporter.domain.interactor.ReportsInteractor
import com.o7.qareporter.getDeviceInfo
import com.o7.qareporter.presentation.mvp.presenter.NewPresenter
import com.o7.qareporter.presentation.mvp.view.NewView
import kotlinx.android.synthetic.main.activity_new.*

class NewActivity : AppCompatActivity() {
    companion object {
        const val REQUEST_CODE_CHOOSER = 1000
        const val REQUEST_CODE_SHARE = 1001
    }

    private lateinit var presenter: NewPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_new)
        supportActionBar?.setHomeAsUpIndicator(R.drawable.ic_close)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        presenter = NewPresenter(NewView(this), ReportsInteractor(ReportRepository(RReportDataSource(), ReportMapper())))
        presenter.init()
        button_new_choose.setOnClickListener {
            presenter.chooseNewImage()
        }
        presenter.getImageFromIntent(intent)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        super.onCreateOptionsMenu(menu)
        menuInflater.inflate(R.menu.new_report_activity, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean =
            if (item?.itemId == R.id.menu_new_send) {
                presenter.saveAndSendReport(getDeviceInfo())
                true
            } else {
                super.onOptionsItemSelected(item)
            }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) =
            presenter.interceptOnActivityResult(requestCode, resultCode, data)

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) =
            presenter.interceptOnRequestPermissionResult(requestCode, permissions, grantResults)
}
