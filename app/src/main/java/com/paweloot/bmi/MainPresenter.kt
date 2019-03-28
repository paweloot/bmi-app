package com.paweloot.bmi

import com.paweloot.bmi.BmiConstants.METRIC_UNITS

class MainPresenter(var view: MainContract.View) : MainContract.Presenter {
    override fun onViewCreated() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onCalculateButtonClicked() {
        calculateBmiResult()
    }

    private fun calculateBmiResult() {
        if (view.isInputValid()) {
            val bmiResult: Double = if (view.getCurrentUnits() == METRIC_UNITS) {
                view.getBmiForKgCm().calcBmi()
            } else {
                view.getBmiForLbFtIn().calcBmi()
            }

            view.displayBmiResult(bmiResult)
        } else {
            view.clearResult()
            view.displayErrorOnEditText()
        }

    }

    override fun onInfoButtonClicked() {
        view.navigateToInfoScreen()
    }

    override fun onSwitchToMetricUnitsClicked() {
        view.clearAllFields()
        view.displayMetricUnits()
    }

    override fun onSwitchToImperialUnitsClicked() {
        view.clearAllFields()
        view.displayImperialUnits()
    }
}