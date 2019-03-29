package com.paweloot.bmi.history

import android.content.SharedPreferences

interface HistoryContract {
    interface Presenter {
        fun fetchHistory(sharedPref: SharedPreferences): ArrayList<Set<String>>?
    }

    interface View {

    }
}