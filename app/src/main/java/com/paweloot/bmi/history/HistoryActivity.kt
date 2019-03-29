package com.paweloot.bmi.history

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import com.paweloot.bmi.R
import kotlinx.android.synthetic.main.activity_history.*

class HistoryActivity : AppCompatActivity(), HistoryContract.View {
    private lateinit var viewManager: RecyclerView.LayoutManager
    private lateinit var viewAdapter: RecyclerView.Adapter<*>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_history)

        val historyData: ArrayList<String> = ArrayList()
        historyData.add("Bebok")
        historyData.add("Werka")

        viewManager = LinearLayoutManager(this)
        viewAdapter = RecyclerAdapter(historyData)

        history_recycler_view.layoutManager = viewManager
        history_recycler_view.adapter = viewAdapter
    }
}