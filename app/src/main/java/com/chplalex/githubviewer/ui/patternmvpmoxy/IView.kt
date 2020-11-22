package com.chplalex.githubviewer.ui.patternmvpmoxy

import moxy.MvpView
import moxy.viewstate.strategy.alias.AddToEnd

@AddToEnd
interface IView : MvpView {
    fun init()
    fun onDataUpdated(index: Int, data: String)
}