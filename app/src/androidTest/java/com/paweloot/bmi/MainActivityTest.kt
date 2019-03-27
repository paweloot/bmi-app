package com.paweloot.bmi


import android.support.test.espresso.Espresso.onView
import android.support.test.espresso.action.ViewActions.*
import android.support.test.espresso.assertion.ViewAssertions.matches
import android.support.test.espresso.matcher.ViewMatchers.withId
import android.support.test.espresso.matcher.ViewMatchers.withText
import android.support.test.filters.LargeTest
import android.support.test.rule.ActivityTestRule
import android.support.test.runner.AndroidJUnit4
import android.view.View
import android.view.ViewGroup
import org.hamcrest.Description
import org.hamcrest.Matcher
import org.hamcrest.TypeSafeMatcher
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@LargeTest
@RunWith(AndroidJUnit4::class)
class MainActivityTest {

    @Rule
    @JvmField
    var mActivityTestRule = ActivityTestRule(MainActivity::class.java)

    @Test
    fun mainActivityTest() {
        val massEditText = onView(withId(R.id.mass_edit))
        massEditText.perform(scrollTo(), replaceText("75"), closeSoftKeyboard())

        val heightEditText = onView(withId(R.id.height_edit))
        heightEditText.perform(scrollTo(), replaceText("180"), closeSoftKeyboard())

        val calculateButton = onView(withId(R.id.calculate_button))
        calculateButton.perform(scrollTo(), click())

        val textView = onView(withId(R.id.bmi_result_text))
        textView.check(matches(withText("23.15")))
    }
}
