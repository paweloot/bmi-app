package com.paweloot.bmi.main

import com.paweloot.bmi.main.BmiConstants.METRIC_UNITS

class MainPresenter(var view: MainContract.View) : MainContract.Presenter {

    override fun onCalculateButtonClick() {
        calculateBmiResult()
    }

    private fun calculateBmiResult() {
        if (view.isInputValid()) {
            val bmiResult: Double =
                if (view.getCurrentUnits() == METRIC_UNITS) view.getBmiForKgCm().calcBmi()
                else view.getBmiForLbFtIn().calcBmi()

            view.displayBmiResult(bmiResult)
        } else {
            view.clearResult()
                view.displayErrorOnEditText()
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