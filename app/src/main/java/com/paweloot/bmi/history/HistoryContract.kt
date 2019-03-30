package com.paweloot.bmi.history

import android.content.SharedPreferences
import org.json.JSONArray

interface HistoryContract {
    interface Presenter {
        fun fetchHistory(sharedPref: SharedPreferences): JSONArray
    }

    interface View {

    }
}