package com.chplalex.githubviewer.ui.patternmvp

class MVPPresenter(val mvpView: MVPView) {
    val mvpModel = MVPModel()

    fun fabClicked() {
        mvpModel.incCounter()
        mvpView.setText(mvpModel.getIndex(), mvpModel.getCounter().toString())
    }
}