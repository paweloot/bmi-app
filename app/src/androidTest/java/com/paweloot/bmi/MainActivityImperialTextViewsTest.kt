package com.paweloot.bmi


import android.support.test.InstrumentationRegistry.getInstrumentation
import android.support.test.espresso.Espresso.onView
import android.support.test.espresso.Espresso.openActionBarOverflowOrOptionsMenu
import android.support.test.espresso.action.ViewActions.click
import android.support.test.espresso.assertion.ViewAssertions.matches
import android.support.test.espresso.matcher.ViewMatchers.*
import android.support.test.filters.LargeTest
import android.support.test.rule.ActivityTestRule
import android.support.test.runner.AndroidJUnit4
import com.paweloot.bmi.main.MainActivity
import org.hamcrest.Matchers.allOf
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@LargeTest
@RunWith(AndroidJUnit4::class)
class MainActivityImperialTextViewsTest {

    @Rule
    @JvmField
    var mActivityTestRule = ActivityTestRule(MainActivity::class.java)

    @Test
    fun mainActivityImperialTextViewsTest() {
        openActionBarOverflowOrOptionsMenu(getInstrumentation().getTargetContext())

        val appCompatTextView = onView(
            allOf(
                withId(R.id.title), withText("Switch to imperial units")
            )
        )
        appCompatTextView.perform(click())

        val massLbTextView = onView(withId(R.id.mass_text))
        massLbTextView.check(matches(withText("Mass [lb]")))

        val heightFtTextView = onView(withId(R.id.height_text))
        heightFtTextView.check(matches(withText("Height [ft]")))

        val heightInTextView = onView(withId(R.id.height_in_text))
        heightInTextView.check(matches(withText("Height [in]")))

        val calculateButton = onView(withId(R.id.calculate_button))
        calculateButton.check(matches(isDisplayed()))
    }
}
