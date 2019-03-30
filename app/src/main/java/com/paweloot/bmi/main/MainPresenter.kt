package com.paweloot.bmi.main

import android.content.SharedPreferences
import com.paweloot.bmi.common.BmiConstants.HISTORY_RECORDS_NUMBER
import com.paweloot.bmi.common.BmiConstants.IMPERIAL_UNITS
import com.paweloot.bmi.common.BmiConstants.METRIC_UNITS
import com.paweloot.bmi.main.logic.Bmi
import com.paweloot.bmi.main.logic.BmiForKgCm
import com.paweloot.bmi.main.logic.BmiForLbFtIn
import org.json.JSONArray
import org.json.JSONObject
import java.text.SimpleDateFormat
import java.util.*

class MainPresenter(val view: MainContract.View) : MainContract.Presenter {

    override fun onCalculateButtonClick() {
        calculateBmiResult()
    }

    private fun calculateBmiResult() {
        if (view.isInputValid()) {
            val bmiResult: Double =
                if (view.getCurrentUnits() == METRIC_UNITS) view.getBmiForKgCm().calcBmi()
                else view.getBmiForLbFtIn().calcBmi()

            view.displayBmiResult(bmiResult)
            view.saveHistoryRecord()
        } else {
            view.clearResult()
            view.displayErrorOnEditText()
        }
    }

    override fun saveHistoryRecord(sharedPref: SharedPreferences, bmiData: Bmi) {
        val historyRecord: JSONObject = createHistoryRecordJSONObject(bmiData)

        val currHistoryRaw = sharedPref.getString("history_data", null)
        val currHistoryData: JSONArray = if (currHistoryRaw == null) JSONArray() else JSONArray(currHistoryRaw)

        if (currHistoryData.length() == HISTORY_RECORDS_NUMBER) currHistoryData.remove(0)
        currHistoryData.put(historyRecord)

        with(sharedPref.edit()) {
            putString("history_data", currHistoryData.toString())
            apply()
        }
    }

    private fun createHistoryRecordJSONObject(bmiData: Bmi): JSONObject {
        val currentDate: String = SimpleDateFormat("MM/dd/yyyy", Locale.getDefault()).format(Date())
        val historyRecord = JSONObject()

        if (view.getCurrentUnits() == METRIC_UNITS) {
            val bmiMetricData = bmiData as BmiForKgCm

            historyRecord.put("units", METRIC_UNITS)
            historyRecord.put("mass", bmiMetricData.mass)
            historyRecord.put("height", bmiMetricData.height)
            historyRecord.put("date", currentDate)
        } else {
            val bmiImperialData = bmiData as BmiForLbFtIn

            historyRecord.put("units", IMPERIAL_UNITS)
            historyRecord.put("mass", bmiImperialData.mass)
            historyRecord.put("height", bmiImperialData.heightFt)
            historyRecord.put("heightIn", bmiImperialData.heightIn)
            historyRecord.put("date", currentDate)
        }

        return historyRecord
    }

    override fun onInfoButtonClick() {
        view.navigateToInfoScreen()
    }

    override fun onOpenHistoryClick() {
        view.navigateToHistoryScreen()
    }

    override fun onSwitchToMetricUnitsClick() {
        view.clearAllFields()
        view.switchToMetricUnits()
    }

    override fun onSwitchToImperialUnitsClick() {
        view.clearAllFields()
        view.switchToImperialUnits()
    }
}