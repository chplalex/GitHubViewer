package com.chplalex.githubviewer.mvp.presenter.list

import com.chplalex.githubviewer.mvp.view.list.IItemView

interface IListPresenter<V : IItemView> {
    var itemClickListener: ((V) -> Unit)?
    fun bindView(view: V)
    fun getCount(): Int
}