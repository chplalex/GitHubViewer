package com.chplalex.githubviewer.mvp.view

import moxy.MvpView
import moxy.viewstate.strategy.alias.AddToEndSingle

@AddToEndSingle
interface UsersView : MvpView {
    fun init()
    fun updateUsersList()
    fun showExitMessage()
    fun destroy()
}