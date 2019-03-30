package com.paweloot.bmi.common

import com.paweloot.bmi.R
import com.paweloot.bmi.common.BmiConstants.BMI_NORMAL_UPPER_BOUND
import com.paweloot.bmi.common.BmiConstants.BMI_OBESE_UPPER_BOUND
import com.paweloot.bmi.common.BmiConstants.BMI_OVERWEIGHT_UPPER_BOUND
import com.paweloot.bmi.common.BmiConstants.BMI_UNDERWEIGHT_UPPER_BOUND

object BmiUtils {
    fun mapBmiToColor(bmiResult: Double): Int {
        return when {
            bmiResult < BMI_UNDERWEIGHT_UPPER_BOUND -> R.color.bmi_underweight
            bmiResult < BMI_NORMAL_UPPER_BOUND -> R.color.bmi_normal
            bmiResult < BMI_OVERWEIGHT_UPPER_BOUND -> R.color.bmi_overweight
            bmiResult < BMI_OBESE_UPPER_BOUND -> R.color.bmi_obese
            else -> R.color.bmi_extremely_obese
        }
    }

    fun mapBmiToCategory(bmiResult: Double): Int {
        return when {
            bmiResult < BMI_UNDERWEIGHT_UPPER_BOUND -> R.string.bmi_main_underweight
            bmiResult < BMI_NORMAL_UPPER_BOUND -> R.string.bmi_main_normal
            bmiResult < BMI_OVERWEIGHT_UPPER_BOUND -> R.string.bmi_main_overweight
            bmiResult < BMI_OBESE_UPPER_BOUND -> R.string.bmi_main_obese
            else -> R.string.bmi_main_extremely_obese
        }
    }

    fun mapBmiToCategoryImage(bmiResult: Double): Int {
        return when {
            bmiResult < BMI_UNDERWEIGHT_UPPER_BOUND -> R.drawable.bmi_underweight
            bmiResult < BMI_NORMAL_UPPER_BOUND -> R.drawable.bmi_normal
            bmiResult < BMI_OVERWEIGHT_UPPER_BOUND -> R.drawable.bmi_overweight
            bmiResult < BMI_OBESE_UPPER_BOUND -> R.drawable.bmi_obese
            else -> R.drawable.bmi_extremely_obese
        }
    }
}