package com.chplalex.githubviewer.mvp.view.list

interface UserItemView : IItemView {
    fun setLogin(login: String)
    fun loadAvatar(url: String)
}