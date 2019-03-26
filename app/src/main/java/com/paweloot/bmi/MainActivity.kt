package com.paweloot.bmi

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import com.paweloot.bmi.logic.BmiForKgCm
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        calculate_button.setOnClickListener {
            bmi_result_text.text = null
            bmi_category_text.text = null

            if (isInputTextValid()) {
                val mass: Int = mass_edit.text.toString().toInt()
                val height: Int = height_edit.text.toString().toInt()

                setBmiResultText(BmiForKgCm(mass, height).calcBmi())
            } else {
                mass_edit.error = when {
                    mass_edit.text.isEmpty() -> getString(R.string.empty_input_error)
                    mass_edit.text.toString().toInt() == 0 -> getString(R.string.zero_input_error)
                    else -> null
                }
                height_edit.error = when {
                    height_edit.text.isEmpty() -> getString(R.string.empty_input_error)
                    height_edit.text.toString().toInt() == 0 -> getString(R.string.zero_input_error)
                    else -> null
                }
            }
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.options_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when (item?.itemId) {
            R.id.about_me -> startActivity(Intent(this, AboutActivity::class.java))
        }

        return super.onOptionsItemSelected(item)
    }

    override fun onSaveInstanceState(outState: Bundle?) {
        outState?.putString("bmiResult", bmi_result_text?.text.toString())
        super.onSaveInstanceState(outState)
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle?) {
        super.onRestoreInstanceState(savedInstanceState)
        
        val bmiResult: Double? = savedInstanceState?.getString("bmiResult")?.toDoubleOrNull()
        if (bmiResult != null) setBmiResultText(bmiResult)
    }

    private fun isInputTextValid(): Boolean {
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
            bmi < 18.5 -> {
                categoryResource = R.string.bmi_category_underweight
                resultColorResource = R.color.colorBmiUnderweight
            }
            bmi < 24.9 -> {
                categoryResource = R.string.bmi_category_normal
                resultColorResource = R.color.colorBmiNormal
            }
            bmi < 29.9 -> {
                categoryResource = R.string.bmi_category_overweight
                resultColorResource = R.color.colorBmiOverweight
            }
            bmi < 34.9 -> {
                categoryResource = R.string.bmi_category_obese
                resultColorResource = R.color.colorBmiObese
            }
            else -> {
                categoryResource = R.string.bmi_category_extremely_obese
                resultColorResource = R.color.colorBmiExtremelyObese
            }
        }

        bmi_category_text.text = getString(categoryResource)
        bmi_result_text.setTextColor(getColor(resultColorResource))
    }
}
