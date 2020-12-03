package com.chplalex.githubviewer.mvp.view

import moxy.MvpView
import moxy.viewstate.strategy.alias.AddToEndSingle

@AddToEndSingle
interface UserView : MvpView {
    fun init()
    fun setLogin(login: String)
    fun setAvatar(url: String)
    fun updateReposList()
}