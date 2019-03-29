package com.paweloot.bmi.info

import android.os.Bundle

class InfoPresenter(var view: InfoContract.View) : InfoContract.Presenter {
    override fun onIntentBundleReceived(intentBundle: Bundle) {
        val bmiResult = intentBundle.getCharSequence("bmiResult")
        val bmiCategory = intentBundle.getCharSequence("bmiCategory")
        val bmiCategoryColor = intentBundle.getInt("bmiCategoryColor")

        view.setCategory(bmiResult.toString().toDouble(), bmiCategory, bmiCategoryColor)
    }
}