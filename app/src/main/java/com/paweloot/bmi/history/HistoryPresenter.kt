package com.paweloot.bmi.history

import android.content.SharedPreferences
import org.json.JSONArray

class HistoryPresenter(val view: HistoryContract.View) : HistoryContract.Presenter {

    override fun fetchHistory(sharedPref: SharedPreferences): JSONArray {
        val historyRaw = sharedPref.getString("history_data", null)

        return if (historyRaw == null) JSONArray()
        else JSONArray(historyRaw)
    }
}