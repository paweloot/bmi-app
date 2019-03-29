package com.paweloot.bmi.main

import com.paweloot.bmi.main.logic.BmiForKgCm
import com.paweloot.bmi.main.logic.BmiForLbFtIn

interface MainContract {
    interface Presenter {
        fun onCalculateButtonClick()
        fun onInfoButtonClick()
        fun onOpenHistoryClick()
        fun onSwitchToMetricUnitsClick()
        fun onSwitchToImperialUnitsClick()
    }

    interface View {
        fun displayBmiResult(bmiResult: Double)
        fun getBmiForKgCm(): BmiForKgCm
        fun getBmiForLbFtIn(): BmiForLbFtIn
        fun getCurrentUnits(): Int

        fun isInputValid(): Boolean
        fun displayErrorOnEditText()
        fun clearAllFields()
        fun clearResult()

        fun navigateToInfoScreen()
        fun navigateToHistoryScreen()
        fun switchToMetricUnits()
        fun switchToImperialUnits()
    }
}