package com.paweloot.bmi.history

import android.content.SharedPreferences
import com.paweloot.bmi.main.MainContract

class HistoryPresenter(val view: HistoryContract.View) : HistoryContract.Presenter {
    private val numberOfHistoryRecords = 10

    override fun fetchHistory(sharedPref: SharedPreferences): ArrayList<String> {
        val history = ArrayList<String>()
        var historyRecord: String?

        for (i in 1..numberOfHistoryRecords) {
            historyRecord = sharedPref.getString("history_record_$i", null)
            if (historyRecord != null) history.add(historyRecord)
        }

        return history
    }
}