package com.paweloot.bmi.history

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.support.v4.app.NavUtils
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.MenuItem
import com.paweloot.bmi.R
import kotlinx.android.synthetic.main.activity_history.*

class HistoryActivity : AppCompatActivity(), HistoryContract.View {
    private lateinit var presenter: HistoryContract.Presenter

    private lateinit var viewManager: RecyclerView.LayoutManager
    private lateinit var viewAdapter: RecyclerView.Adapter<*>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_history)

        setUpActionBar()

        presenter = HistoryPresenter(this)

        viewManager = LinearLayoutManager(this).apply { stackFromEnd = true; reverseLayout = true }
        viewAdapter = HistoryRecyclerAdapter(presenter.fetchHistory(fetchSharedPref()))

        history_recycler_view.layoutManager = viewManager
        history_recycler_view.adapter = viewAdapter
    }

    private fun setUpActionBar() {
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setTitle(R.string.bmi_history_action_bar_title)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when (item?.itemId) {
            R.id.home -> NavUtils.navigateUpFromSameTask(this)
        }

        return super.onOptionsItemSelected(item)
    }

    private fun fetchSharedPref(): SharedPreferences {
        return getSharedPreferences(
            getString(R.string.bmi_history_sharedpref),
            Context.MODE_PRIVATE
        )
    }
}