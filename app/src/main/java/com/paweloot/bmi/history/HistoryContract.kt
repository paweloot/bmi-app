package com.paweloot.bmi.history

import android.content.SharedPreferences

interface HistoryContract {
    interface Presenter {
        fun fetchHistory(sharedPref: SharedPreferences): ArrayList<String>
    }

    interface View {

    }
}