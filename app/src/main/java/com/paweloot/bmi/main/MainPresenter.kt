package com.paweloot.bmi.main

import android.content.SharedPreferences
import com.paweloot.bmi.R
import com.paweloot.bmi.main.BmiConstants.IMPERIAL_UNITS
import com.paweloot.bmi.main.BmiConstants.METRIC_UNITS
import com.paweloot.bmi.main.logic.Bmi
import com.paweloot.bmi.main.logic.BmiForKgCm
import com.paweloot.bmi.main.logic.BmiForLbFtIn
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
        val currentDate: String = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(Date())
        val historyRecord: Set<String>

        historyRecord = if (view.getCurrentUnits() == METRIC_UNITS) {
            val bmiMetricData = bmiData as BmiForKgCm
            setOf(
                METRIC_UNITS.toString(), bmiMetricData.mass.toString(),
                bmiMetricData.height.toString(), currentDate
            )
        } else {
            val bmiImperialData = bmiData as BmiForLbFtIn
            setOf(
                IMPERIAL_UNITS.toString(), bmiImperialData.mass.toString(),
                bmiImperialData.heightFt.toString(), bmiImperialData.heightIn.toString(), currentDate
            )
        }

        with(sharedPref.edit()) {
            putStringSet("history_record_1", historyRecord)
            apply()
        }
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