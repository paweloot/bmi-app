package com.paweloot.bmi.about

class AboutPresenter(val view: AboutContract.View) : AboutContract.Presenter {
    override fun onDoneButtonClick() {
        view.displayWelcomeToast()
    }
}