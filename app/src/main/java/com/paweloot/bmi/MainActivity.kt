package com.paweloot.bmi

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.app.NavUtils
import android.view.Menu
import android.view.MenuItem
import com.paweloot.bmi.logic.BmiForKgCm
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        calcBTN.setOnClickListener {
            bmiResultTV.text = null
            categoryTV.text = null

            if (isInputTextValid()) {
                val mass: Int = massET.text.toString().toInt()
                val height: Int = heightET.text.toString().toInt()

                setBmiResultText(BmiForKgCm(mass, height).calcBmi())
            } else {
                massET.error = when {
                    massET.text.isEmpty() -> getString(R.string.empty_input_error)
                    massET.text.toString().toInt() == 0 -> getString(R.string.zero_input_error)
                    else -> null
                }
                heightET.error = when {
                    heightET.text.isEmpty() -> getString(R.string.empty_input_error)
                    heightET.text.toString().toInt() == 0 -> getString(R.string.zero_input_error)
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
        outState?.putString("bmiResult", bmiResultTV.text.toString())
        super.onSaveInstanceState(outState)
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle?) {
        super.onRestoreInstanceState(savedInstanceState)
        setBmiResultText(savedInstanceState?.getString("bmiResult")?.toDouble()!!)
    }

    private fun isInputTextValid(): Boolean {
        return !(massET.text.isEmpty() ||
                massET.text.toString().toInt() == 0 ||
                heightET.text.isEmpty() ||
                heightET.text.toString().toInt() == 0)
    }

    private fun setBmiResultText(bmi: Double) {
        bmiResultTV.text = String.format("%.2f", bmi)

        val category: String
        val resultColor: Int
        when {
            bmi < 18.5 -> {
                category = getString(R.string.bmi_category_underweight)
                resultColor = getColor(R.color.colorBmiUnderweight)
            }
            bmi < 24.9 -> {
                category = getString(R.string.bmi_category_normal)
                resultColor = getColor(R.color.colorBmiNormal)
            }
            bmi < 29.9 -> {
                category = getString(R.string.bmi_category_overweight)
                resultColor = getColor(R.color.colorBmiOverweight)
            }
            bmi < 34.9 -> {
                category = getString(R.string.bmi_category_obese)
                resultColor = getColor(R.color.colorBmiObese)
            }
            else -> {
                category = getString(R.string.bmi_category_extremely_obese)
                resultColor = getColor(R.color.colorBmiExtremelyObese)
            }
        }

        categoryTV.text = category
        bmiResultTV.setTextColor(resultColor)
    }
}
