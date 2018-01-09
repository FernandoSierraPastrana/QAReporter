package com.o7.qareporter.presentation.adapter

import android.support.v7.widget.RecyclerView
import android.util.SparseArray
import android.view.ViewGroup
import com.o7.qareporter.domain.model.Report
import com.o7.qareporter.presentation.adapter.delegate.RecyclerDelegate
import com.o7.qareporter.presentation.adapter.delegate.ReportDelegate
import com.o7.qareporter.presentation.adapter.delegate.ReportViewType
import com.o7.qareporter.presentation.adapter.delegate.ViewType

class ReportsAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    private val dataSet = mutableListOf<ViewType>()
    private val sparseArrayDelegates = SparseArray<RecyclerDelegate>()

    init {
        sparseArrayDelegates.put(ReportViewType.TYPE, ReportDelegate())
    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): RecyclerView.ViewHolder =
            sparseArrayDelegates[viewType].onCreateViewHolder(parent)

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder?, position: Int) =
            sparseArrayDelegates[getItemViewType(position)].onBindViewHolder(holder, dataSet[position])

    override fun getItemViewType(position: Int): Int = ReportViewType.TYPE

    override fun getItemCount(): Int = dataSet.size

    fun addAll(items: List<Report>) {
        dataSet.clear()
        dataSet.addAll(items.map { ReportViewType(it) })
    }
}