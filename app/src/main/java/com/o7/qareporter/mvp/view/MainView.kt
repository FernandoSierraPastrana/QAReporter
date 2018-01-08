package com.o7.qareporter.mvp.view

import android.content.Intent
import android.support.design.widget.FloatingActionButton
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import com.o7.qareporter.R
import com.o7.qareporter.mvp.activity.MainActivity
import com.o7.qareporter.mvp.activity.NewActivity
import java.lang.ref.WeakReference

class MainView(activity: MainActivity) {
    private val activityRef = WeakReference(activity)
    private lateinit var recyclerView: RecyclerView
    private lateinit var fabNew: FloatingActionButton

    fun init() {
        val activity = activityRef.get()
        if (activity != null) {
            recyclerView = activity.findViewById(R.id.recycler_main_history)
            fabNew = activity.findViewById(R.id.fab_main_new)

            recyclerView.setHasFixedSize(true)
            recyclerView.layoutManager = LinearLayoutManager(activity)
            recyclerView.addItemDecoration(DividerItemDecoration(activity, DividerItemDecoration.VERTICAL))

            fabNew.setOnClickListener {
                activity.startActivity(Intent(activity, NewActivity::class.java))
            }
        }
    }
}