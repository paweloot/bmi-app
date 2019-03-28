package com.paweloot.bmi.about

import android.os.Bundle
import android.support.v4.app.NavUtils
import android.support.v7.app.AppCompatActivity
import android.view.MenuItem
import android.widget.Toast
import com.paweloot.bmi.R
import kotlinx.android.synthetic.main.activity_about.*

class AboutActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_about)

        setUpActionBar()

        done_button.setOnClickListener {
            val name = if (name_edit.text.isNotEmpty()) name_edit.text else getString(R.string.bmi_about_mysterious_wanderer)
            Toast.makeText(this, "Nice to meet you %s".format(name), Toast.LENGTH_SHORT).show()


        }
    }

    private fun setUpActionBar() {
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setTitle(R.string.bmi_about_action_bar_title)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when (item?.itemId) {
            R.id.home -> NavUtils.navigateUpFromSameTask(this)
        }

        return super.onOptionsItemSelected(item)
    }


}