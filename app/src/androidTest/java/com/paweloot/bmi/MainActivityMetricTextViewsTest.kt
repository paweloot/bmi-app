package com.paweloot.bmi


import android.support.test.espresso.Espresso.onView
import android.support.test.espresso.assertion.ViewAssertions.matches
import android.support.test.espresso.matcher.ViewMatchers.*
import android.support.test.filters.LargeTest
import android.support.test.rule.ActivityTestRule
import android.support.test.runner.AndroidJUnit4
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@LargeTest
@RunWith(AndroidJUnit4::class)
class MainActivityMetricTextViewsTest {

    @Rule
    @JvmField
    var mActivityTestRule = ActivityTestRule(MainActivity::class.java)

    @Test
    fun mainActivityMetricTextViewsTest() {
        val massTextView = onView(withId(R.id.mass_text))
        massTextView.check(matches(withText("Mass [kg]")))

        val heightTextView = onView(withId(R.id.height_text))
        heightTextView.check(matches(withText("Height [cm]")))

        val calculateButton = onView(withId(R.id.calculate_button))
        calculateButton.check(matches(isDisplayed()))
    }
}
