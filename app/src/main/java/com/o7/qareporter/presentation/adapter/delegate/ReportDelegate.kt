package com.o7.qareporter.presentation.adapter.delegate

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.o7.qareporter.R

class ReportDelegate : RecyclerDelegate {
    companion object {
        class ReportHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
            val imagePreview = itemView.findViewById(R.id.image_item) as ImageView
            val textDescription = itemView.findViewById(R.id.text_item_description) as TextView
            val textDate = itemView.findViewById(R.id.text_item_date) as TextView
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup?): RecyclerView.ViewHolder =
            ReportHolder(LayoutInflater.from(parent?.context).inflate(R.layout.item_report, parent, false))

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder?, item: Any) {
        if (holder != null && holder is ReportHolder && item is ReportViewType) {
            holder.imagePreview.setImageURI(item.imageUri)
            holder.textDescription.text = item.description
            holder.textDate.text = item.date
        }
    }
}