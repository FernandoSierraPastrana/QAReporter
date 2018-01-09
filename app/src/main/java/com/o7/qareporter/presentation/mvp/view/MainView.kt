package com.o7.qareporter.presentation.mvp.view

import android.content.Intent
import android.support.design.widget.FloatingActionButton
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewStub
import com.o7.qareporter.R
import com.o7.qareporter.domain.model.Report
import com.o7.qareporter.presentation.adapter.ReportsAdapter
import com.o7.qareporter.presentation.mvp.activity.MainActivity
import com.o7.qareporter.presentation.mvp.activity.NewActivity
import java.lang.ref.WeakReference

class MainView(activity: MainActivity) {
    private val activityRef = WeakReference(activity)
    private lateinit var recyclerView: RecyclerView
    private lateinit var fabNew: FloatingActionButton
    private lateinit var emptyView: View
    private val adapter = ReportsAdapter()
    fun init() {
        val activity = activityRef.get()
        if (activity != null) {
            emptyView = activity.findViewById(R.id.stub_main_empty)
            recyclerView = activity.findViewById(R.id.recycler_main_history)
            fabNew = activity.findViewById(R.id.fab_main_new)

            recyclerView.setHasFixedSize(true)
            recyclerView.layoutManager = LinearLayoutManager(activity)
            recyclerView.addItemDecoration(DividerItemDecoration(activity, DividerItemDecoration.VERTICAL))
            recyclerView.adapter = adapter

            fabNew.setOnClickListener {
                activity.startActivity(Intent(activity, NewActivity::class.java))
            }
        }
    }

    fun updateReports(reports: List<Report>) {
        emptyView.visibility = View.GONE
        recyclerView.visibility = View.VISIBLE
        adapter.addAll(reports)
    }

    fun showEmptyView() {
        if (emptyView is ViewStub) {
            emptyView = (emptyView as ViewStub).inflate()
        }
        emptyView.visibility = View.VISIBLE
        recyclerView.visibility = View.GONE
    }
}