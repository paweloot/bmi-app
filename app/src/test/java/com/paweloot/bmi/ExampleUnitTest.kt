package com.paweloot.bmi

import com.paweloot.bmi.main.logic.BmiForKgCm
import com.paweloot.bmi.main.logic.BmiForLbFtIn
import org.junit.Test

import org.junit.Assert.*

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {
    @Test
    fun forValidMetricUnitsDataShouldCalculateBmi() {
        var bmi = BmiForKgCm(65, 170)
        assertEquals(22.491, bmi.calcBmi(), 0.001)

        bmi = BmiForKgCm(80, 182)
        assertEquals(24.15, bmi.calcBmi(), 0.01)

        bmi = BmiForKgCm(54, 175)
        assertEquals(17.63, bmi.calcBmi(), 0.01)

        bmi = BmiForKgCm(90, 168)
        assertEquals(31.89, bmi.calcBmi(), 0.01)

        bmi = BmiForKgCm(103, 192)
        assertEquals(27.94, bmi.calcBmi(), 0.01)

        bmi = BmiForKgCm(103, 169)
        assertEquals(36.06, bmi.calcBmi(), 0.01)
    }

    @Test
    fun forValidImperialUnitsDataShouldCalculateBmi() {
        var bmi = BmiForLbFtIn(120, 5, 0)
        assertEquals(23.43, bmi.calcBmi(), 0.01)

        bmi = BmiForLbFtIn(129, 5, 9)
        assertEquals(19.05, bmi.calcBmi(), 0.01)

        bmi = BmiForLbFtIn(194, 4, 11)
        assertEquals(39.18, bmi.calcBmi(), 0.01)

        bmi = BmiForLbFtIn(169, 5, 6)
        assertEquals(27.27, bmi.calcBmi(), 0.01)

        bmi = BmiForLbFtIn(180, 6, 3)
        assertEquals(22.50, bmi.calcBmi(), 0.01)

        bmi = BmiForLbFtIn(230, 5, 4)
        assertEquals(39.48, bmi.calcBmi(), 0.01)
    }
}
