package com.paweloot.bmi.logic

class BmiForKgCm(var mass: Int, var height: Int) : Bmi {
    override fun calcBmi(): Double {
        return mass * 10000.0 / (height * height)
    }
}