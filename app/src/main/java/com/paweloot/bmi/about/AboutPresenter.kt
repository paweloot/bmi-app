package com.paweloot.bmi.about

class AboutPresenter(var view: AboutContract.View) : AboutContract.Presenter {
    override fun onDoneButtonClick() {
        view.displayWelcomeToast()
    }
}