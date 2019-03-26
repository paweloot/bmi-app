package com.paweloot.bmi

import android.os.Bundle
import android.support.v4.app.NavUtils
import android.support.v7.app.AppCompatActivity
import android.view.MenuItem
import android.view.View
import android.widget.Button
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_about.*

class AboutActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_about)

        showBackArrowOnActionBar()

        done_button.setOnClickListener {
            val name = if (name_edit.text.isNotEmpty()) name_edit.text else getString(R.string.mysterious_wanderer)
            Toast.makeText(this, "Nice to meet you %s".format(name), Toast.LENGTH_SHORT).show()


        }
    }

    private fun showBackArrowOnActionBar() {
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when (item?.itemId) {
            R.id.home -> NavUtils.navigateUpFromSameTask(this)
        }

        return super.onOptionsItemSelected(item)
    }


}