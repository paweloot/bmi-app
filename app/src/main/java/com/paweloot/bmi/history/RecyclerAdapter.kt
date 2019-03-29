package com.paweloot.bmi.history

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.paweloot.bmi.R
import kotlinx.android.synthetic.main.recyclerview_item.view.*

class RecyclerAdapter(private val historyData: ArrayList<String>) :
    RecyclerView.Adapter<RecyclerAdapter.HistoryViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerAdapter.HistoryViewHolder {
        val inflatedView = LayoutInflater.from(parent.context)
            .inflate(R.layout.recyclerview_item, parent, false) as View

        return HistoryViewHolder(inflatedView)
    }

    override fun onBindViewHolder(holder: HistoryViewHolder, position: Int) {
        holder.setText(historyData[position])
    }

    override fun getItemCount() = historyData.size

    class HistoryViewHolder(val view: View) : RecyclerView.ViewHolder(view) {
        fun setText(text: String?) {
            view.textView.text = text
        }
    }
}
