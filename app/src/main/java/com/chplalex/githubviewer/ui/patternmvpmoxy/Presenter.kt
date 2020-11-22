package com.chplalex.githubviewer.ui.patternmvpmoxy

import moxy.MvpPresenter

class Presenter : MvpPresenter<IView>() {

    private lateinit var model: Model

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        viewState.init()
        model =  Model.getInstance()
    }

    fun fabClicked() {
        model.incCounter()
        viewState.onDataUpdated(model.getIndex(), model.getCounter().toString())
    }
}