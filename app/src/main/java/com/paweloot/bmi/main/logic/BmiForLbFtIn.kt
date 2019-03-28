package com.paweloot.bmi.main.logic

class BmiForLbFtIn(var mass: Int, var heightFt: Int, var heightIn: Int): Bmi {
    override fun calcBmi(): Double {
        val totalHeightIn: Int = heightFt * 12 + heightIn
        return (703.0 * mass) / (totalHeightIn * totalHeightIn)
    }
}