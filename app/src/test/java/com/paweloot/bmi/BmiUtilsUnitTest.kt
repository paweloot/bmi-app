package com.paweloot.bmi

import com.paweloot.bmi.common.BmiUtils
import org.junit.Test
import org.junit.Assert.*

class BmiUtilsUnitTest {
    @Test
    fun forBmiResultShouldReturnCategoryColor() {
        assertEquals(BmiUtils.mapBmiToColor(17.63), R.color.bmi_underweight)
        assertEquals(BmiUtils.mapBmiToColor(21.33), R.color.bmi_normal)
        assertEquals(BmiUtils.mapBmiToColor(28.01), R.color.bmi_overweight)
        assertEquals(BmiUtils.mapBmiToColor(33.11), R.color.bmi_obese)
        assertEquals(BmiUtils.mapBmiToColor(36.39), R.color.bmi_extremely_obese)
    }

    @Test
    fun forBmiResultShouldReturnCategory() {
        assertEquals(BmiUtils.mapBmiToCategory(17.63), R.string.bmi_main_underweight)
        assertEquals(BmiUtils.mapBmiToCategory(21.33), R.string.bmi_main_normal)
        assertEquals(BmiUtils.mapBmiToCategory(28.01), R.string.bmi_main_overweight)
        assertEquals(BmiUtils.mapBmiToCategory(33.11), R.string.bmi_main_obese)
        assertEquals(BmiUtils.mapBmiToCategory(36.39), R.string.bmi_main_extremely_obese)
    }

    @Test
    fun forBmiResultShouldReturnCategoryImage() {
        assertEquals(BmiUtils.mapBmiToCategoryImage(17.63), R.drawable.bmi_underweight)
        assertEquals(BmiUtils.mapBmiToCategoryImage(21.33), R.drawable.bmi_normal)
        assertEquals(BmiUtils.mapBmiToCategoryImage(28.01), R.drawable.bmi_overweight)
        assertEquals(BmiUtils.mapBmiToCategoryImage(33.11), R.drawable.bmi_obese)
        assertEquals(BmiUtils.mapBmiToCategoryImage(36.39), R.drawable.bmi_extremely_obese)
    }
}