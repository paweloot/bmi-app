package com.paweloot.bmi.history

import android.content.Context
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import com.paweloot.bmi.R
import kotlinx.android.synthetic.main.activity_history.*

class HistoryActivity : AppCompatActivity(), HistoryContract.View {
    private lateinit var presenter: HistoryContract.Presenter

    private lateinit var viewManager: RecyclerView.LayoutManager
    private lateinit var viewAdapter: RecyclerView.Adapter<*>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_history)

//        val historyData: ArrayList<String> = ArrayList()
//        historyData.add("Bebok")
//        historyData.add("Werka")

        presenter = HistoryPresenter(this)

        val sharedPref = getSharedPreferences(
            getString(R.string.bmi_history_sharedpref),
            Context.MODE_PRIVATE
        )

        viewManager = LinearLayoutManager(this).apply { stackFromEnd = true; reverseLayout = true }
        viewAdapter = RecyclerAdapter(presenter.fetchHistory(sharedPref))

        history_recycler_view.layoutManager = viewManager
        history_recycler_view.adapter = viewAdapter


    }


}