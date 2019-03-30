package com.paweloot.bmi.info

import android.os.Bundle
import android.support.v4.app.NavUtils
import android.support.v7.app.AppCompatActivity
import android.view.MenuItem
import com.paweloot.bmi.R
import com.paweloot.bmi.common.BmiUtils
import kotlinx.android.synthetic.main.activity_info.*

class InfoActivity : AppCompatActivity(), InfoContract.View {

    private lateinit var presenter: InfoPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_info)

        setUpActionBar()

        presenter = InfoPresenter(this)
        presenter.onIntentBundleReceived(intent.extras)
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

    override fun setCategory(bmiResult: Double, bmiCategory: CharSequence, bmiCategoryColor: Int) {
        setCategoryImage(bmiResult)

        category_text.text = "%s - %s".format(bmiCategory, bmiResult)
        category_text.setTextColor(bmiCategoryColor)
    }

    private fun setCategoryImage(bmiResult: Double) {
        val categoryImage: Int = BmiUtils.mapBmiToCategoryImage(bmiResult)
        category_image.setImageResource(categoryImage)
    }
}