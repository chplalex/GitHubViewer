package com.chplalex.githubviewer.mvp.view.list

interface IUserReposItemView : IItemView {
    fun setName(name: String)
    fun setForksCount(forksCount: Int)
}