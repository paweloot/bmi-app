package com.paweloot.bmi.about

import android.os.Bundle
import android.support.v4.app.NavUtils
import android.support.v7.app.AppCompatActivity
import android.view.MenuItem
import android.widget.Toast
import com.paweloot.bmi.R
import kotlinx.android.synthetic.main.activity_about.*

class AboutActivity : AppCompatActivity(), AboutContract.View {
    private lateinit var presenter: AboutPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_about)

        setUpActionBar()

        presenter = AboutPresenter(this)

        done_button.setOnClickListener { presenter.onDoneButtonClick() }
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

    override fun displayWelcomeToast() {
        val name =
            if (name_edit.text.isNotEmpty()) name_edit.text else getString(R.string.bmi_about_mysterious_wanderer)
        Toast.makeText(this, "Nice to meet you %s".format(name), Toast.LENGTH_SHORT).show()
    }
}