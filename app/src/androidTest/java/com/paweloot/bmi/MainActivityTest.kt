package com.paweloot.bmi


import android.support.test.InstrumentationRegistry
import android.support.test.espresso.Espresso
import android.support.test.espresso.Espresso.onView
import android.support.test.espresso.Espresso.openActionBarOverflowOrOptionsMenu
import android.support.test.espresso.action.ViewActions.*
import android.support.test.espresso.assertion.ViewAssertions.matches
import android.support.test.espresso.matcher.ViewMatchers.withId
import android.support.test.espresso.matcher.ViewMatchers.withText
import android.support.test.filters.LargeTest
import android.support.test.rule.ActivityTestRule
import android.support.test.runner.AndroidJUnit4
import com.paweloot.bmi.main.MainActivity
import org.hamcrest.Matchers
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


        openActionBarOverflowOrOptionsMenu(InstrumentationRegistry.getInstrumentation().targetContext)
        val appCompatTextView = onView(
            Matchers.allOf(
                withId(R.id.title), withText("Switch to imperial units")
            )
        )
        appCompatTextView.perform(click())

        massEditText.perform(scrollTo(), replaceText("150"), closeSoftKeyboard())

        heightEditText.perform(scrollTo(), replaceText("5"), closeSoftKeyboard())

        val heightInEditText = onView(withId(R.id.height_in_edit))
        heightInEditText.perform(scrollTo(), replaceText("4"), closeSoftKeyboard())

        calculateButton.perform(scrollTo(), click())

        textView.check(matches(withText("25.74")))
    }


}
