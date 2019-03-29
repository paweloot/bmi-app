package com.paweloot.bmi.history

import android.content.SharedPreferences
import com.paweloot.bmi.main.MainContract

class HistoryPresenter(val view: MainContract.View) : HistoryContract.Presenter {
    private val numberOfHistoryRecords = 10

    override fun fetchHistory(sharedPref: SharedPreferences): ArrayList<Set<String>>? {
        val fetched: Set<String> = sharedPref.getStringSet("first", null)

        return null
    }
}