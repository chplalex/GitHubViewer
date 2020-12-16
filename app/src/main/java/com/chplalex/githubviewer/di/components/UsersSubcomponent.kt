package com.chplalex.githubviewer.di.components

import com.chplalex.githubviewer.di.modules.UsersModule
import com.chplalex.githubviewer.di.scopes.UsersScope
import com.chplalex.githubviewer.mvp.presenter.UsersPresenter
import com.chplalex.githubviewer.ui.adapter.UsersRvAdapter
import dagger.Subcomponent

@UsersScope
@Subcomponent(
    modules = [
        UsersModule::class
    ]
)
interface UsersSubcomponent {
    fun reposSubcomponent(): UserSubcomponent
    fun inject(usersPresenter: UsersPresenter)
    fun inject(viewHolder: UsersRvAdapter.ViewHolder)
}