package com.paweloot.bmi

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import android.view.View
import com.paweloot.bmi.BmiConstants.BMI_NORMAL_UPPER_BOUND
import com.paweloot.bmi.BmiConstants.BMI_OBESE_UPPER_BOUND
import com.paweloot.bmi.BmiConstants.BMI_OVERWEIGHT_UPPER_BOUND
import com.paweloot.bmi.BmiConstants.BMI_UNDERWEIGHT_UPPER_BOUND
import com.paweloot.bmi.logic.BmiForKgCm
import com.paweloot.bmi.logic.BmiForLbFtIn
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    private val METRIC_UNITS = 0
    private val IMPERIAL_UNITS = 1

    var currentUnits = METRIC_UNITS

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setForwardArrowButtonOnClickListener()
        setCalculateButtonOnClickListener()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_overflow, menu)

        val imperialUnitsMenuItem: MenuItem? = menu?.findItem(R.id.switch_to_imperial_units)
        val metricUnitsMenuItem: MenuItem? = menu?.findItem(R.id.switch_to_metric_units)

        if (currentUnits == METRIC_UNITS) {
            imperialUnitsMenuItem?.isVisible = true
            metricUnitsMenuItem?.isVisible = false
        } else {
            imperialUnitsMenuItem?.isVisible = false
            metricUnitsMenuItem?.isVisible = true
        }

        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when (item?.itemId) {
            R.id.about_me -> startActivity(Intent(this, AboutActivity::class.java))
            R.id.switch_to_imperial_units -> {
                switchToImperialUnits()
                invalidateOptionsMenu()
            }
            R.id.switch_to_metric_units -> {
                switchToMetricUnits()
                invalidateOptionsMenu()
            }
        }

        return super.onOptionsItemSelected(item)
    }

    private fun switchToImperialUnits() {
        currentUnits = IMPERIAL_UNITS

        mass_text.text = getString(R.string.bmi_main_mass_lb)
        height_in_edit.visibility = View.VISIBLE
        height_in_text.visibility = View.VISIBLE
        height_text.text = getString(R.string.bmi_main_height_ft)
    }

    private fun switchToMetricUnits() {
        currentUnits = METRIC_UNITS

        mass_text.text = getString(R.string.bmi_main_mass_kg)
        height_in_text.visibility = View.GONE
        height_in_edit.visibility = View.GONE
        height_text.text = getString(R.string.bmi_main_height_cm)
    }

    override fun onSaveInstanceState(outState: Bundle?) {
        outState?.putString("bmiResult", bmi_result_text.text.toString())
        outState?.putInt("currentUnits", currentUnits)
        super.onSaveInstanceState(outState)
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle?) {
        super.onRestoreInstanceState(savedInstanceState)

        val bmiResult: Double? = savedInstanceState?.getString("bmiResult")?.toDoubleOrNull()
        if (bmiResult != null) {
            setBmiResultText(bmiResult)
            forward_arrow_button.visibility = View.VISIBLE
        }

        val currentUnitsRestored: Int? = savedInstanceState?.getInt("currentUnits")
        if (currentUnitsRestored != null) currentUnits = currentUnitsRestored
        if (currentUnits == IMPERIAL_UNITS) switchToImperialUnits()
    }

    private fun setForwardArrowButtonOnClickListener() {
        forward_arrow_button.setOnClickListener {
            val intentInfoActivity = Intent(this, InfoActivity::class.java)
            intentInfoActivity.putExtra("bmiResult", bmi_result_text.text)
            intentInfoActivity.putExtra("bmiCategory", bmi_category_text.text)
            intentInfoActivity.putExtra("bmiCategoryColor", bmi_result_text.currentTextColor)

            startActivity(intentInfoActivity)
        }
    }

    private fun setCalculateButtonOnClickListener() {
        calculate_button.setOnClickListener {
            bmi_result_text.text = null
            bmi_category_text.text = null
            forward_arrow_button.visibility = View.GONE

            if (currentUnits == METRIC_UNITS) {
                calculateBmiForMetricUnits()
            } else if (currentUnits == IMPERIAL_UNITS) {
                calculateBmiForImperialUnits()
            }
        }
    }

    private fun calculateBmiForMetricUnits() {
        if (isInputValid()) {
            val mass: Int = mass_edit.text.toString().toInt()
            val height: Int = height_edit.text.toString().toInt()

            setBmiResultText(BmiForKgCm(mass, height).calcBmi())
            forward_arrow_button.visibility = View.VISIBLE
        } else displayErrorOnEditText()
    }

    private fun calculateBmiForImperialUnits() {
        if (isInputValid()) {
            val mass: Int = mass_edit.text.toString().toInt()
            val heightFt: Int = height_edit.text.toString().toInt()

            var heightIn: Int? = height_in_edit.text.toString().toIntOrNull()
            heightIn = heightIn ?: 0

            setBmiResultText(BmiForLbFtIn(mass, heightFt, heightIn).calcBmi())
            forward_arrow_button.visibility = View.VISIBLE
        } else displayErrorOnEditText()
    }

    private fun displayErrorOnEditText() {
        mass_edit.error = when {
            mass_edit.text.isEmpty() -> getString(R.string.bmi_main_empty_input_error)
            mass_edit.text.toString().toInt() == 0 -> getString(R.string.bmi_main_zero_input_error)
            else -> null
        }
        height_edit.error = when {
            height_edit.text.isEmpty() -> getString(R.string.bmi_main_empty_input_error)
            height_edit.text.toString().toInt() == 0 -> getString(R.string.bmi_main_zero_input_error)
            else -> null
        }
    }

    private fun isInputValid(): Boolean {
        return !(mass_edit.text.isEmpty() ||
                mass_edit.text.toString().toInt() == 0 ||
                height_edit.text.isEmpty() ||
                height_edit.text.toString().toInt() == 0)
    }

    private fun setBmiResultText(bmi: Double) {
        bmi_result_text.text = String.format("%.2f", bmi)

        val categoryResource: Int
        val resultColorResource: Int
        when {
            bmi < BMI_UNDERWEIGHT_UPPER_BOUND -> {
                categoryResource = R.string.bmi_main_underweight
                resultColorResource = R.color.colorBmiUnderweight
            }
            bmi < BMI_NORMAL_UPPER_BOUND -> {
                categoryResource = R.string.bmi_main_normal
                resultColorResource = R.color.colorBmiNormal
            }
            bmi < BMI_OVERWEIGHT_UPPER_BOUND -> {
                categoryResource = R.string.bmi_main_overweight
                resultColorResource = R.color.colorBmiOverweight
            }
            bmi < BMI_OBESE_UPPER_BOUND -> {
                categoryResource = R.string.bmi_main_obese
                resultColorResource = R.color.colorBmiObese
            }
            else -> {
                categoryResource = R.string.bmi_main_extremely_obese
                resultColorResource = R.color.colorBmiExtremelyObese
            }
        }

        bmi_category_text.text = getString(categoryResource)
        bmi_result_text.setTextColor(getColor(resultColorResource))
    }
}
