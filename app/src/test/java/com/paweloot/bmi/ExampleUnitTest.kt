package com.paweloot.bmi

import com.paweloot.bmi.main.logic.BmiForKgCm
import org.junit.Test

import org.junit.Assert.*

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {
    @Test
    fun forValidDataShouldCountBmi() {
        val bmi = BmiForKgCm(65, 170)
        assertEquals(22.491, bmi.calcBmi(), 0.001)
    }
}
