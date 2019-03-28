package com.paweloot.bmi.about

interface AboutContract {
    interface Presenter {
        fun onDoneButtonClick()
    }

    interface View {
        fun displayWelcomeToast()
    }
}