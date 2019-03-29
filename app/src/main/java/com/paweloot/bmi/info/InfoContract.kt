package com.paweloot.bmi.info

import android.os.Bundle

interface InfoContract {
    interface Presenter {
        fun onIntentBundleReceived(intentBundle: Bundle)
    }

    interface View {
        fun setCategory(bmiResult: Double, bmiCategory: CharSequence, bmiCategoryColor: Int)
    }
}