package com.paweloot.bmi

import android.media.Image
import android.os.Bundle
import android.support.v4.app.NavUtils
import android.support.v7.app.AppCompatActivity
import android.view.MenuItem
import kotlinx.android.synthetic.main.activity_info.*

class InfoActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_info)

        setUpActionBar()

        val intentBundle: Bundle = intent.extras
        val bmiResult = intentBundle.getCharSequence("bmiResult")
        val bmiCategory = intentBundle.getCharSequence("bmiCategory")
        val bmiCategoryColor = intentBundle.getInt("bmiCategoryColor")

        setCategoryImage(bmiResult.toString().toDouble())

        category_text.text = "%s - %s".format(bmiCategory, bmiResult)
        category_text.setTextColor(bmiCategoryColor)

    }

    private fun setUpActionBar() {
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setTitle(R.string.bmi_info_action_bar_title)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when (item?.itemId) {
            R.id.home -> NavUtils.navigateUpFromSameTask(this)
        }

        return super.onOptionsItemSelected(item)
    }

    private fun setCategoryImage(bmi: Double) {
        val categoryImage: Int = when {
            bmi < 18.5 -> R.drawable.bmi_underweight
            bmi < 24.9 -> R.drawable.bmi_normal
            bmi < 29.9 -> R.drawable.bmi_overweight
            bmi < 34.9 -> R.drawable.bmi_obese
            else -> R.drawable.bmi_extremely_obese
        }

        category_image.setImageResource(categoryImage)
    }
}