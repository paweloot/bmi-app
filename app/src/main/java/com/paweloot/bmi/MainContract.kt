package com.paweloot.bmi

import com.paweloot.bmi.logic.BmiForKgCm
import com.paweloot.bmi.logic.BmiForLbFtIn

interface MainContract {
    interface Presenter {
        fun onViewCreated()
        fun onCalculateButtonClicked()
        fun onInfoButtonClicked()
        fun onSwitchToMetricUnitsClicked()
        fun onSwitchToImperialUnitsClicked()
    }

    interface View {
        fun displayBmiResult(bmiResult: Double?)
        fun clearAllFields()
        fun clearResult()
        fun getBmiForKgCm(): BmiForKgCm
        fun getBmiForLbFtIn(): BmiForLbFtIn
        fun getCurrentUnits(): Int
        fun isInputValid(): Boolean
        fun displayErrorOnEditText()
        fun navigateToInfoScreen()
        fun displayMetricUnits()
        fun displayImperialUnits()
    }
}