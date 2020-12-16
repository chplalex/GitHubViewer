package com.chplalex.githubviewer.di.components

import com.chplalex.githubviewer.di.modules.UserModule
import com.chplalex.githubviewer.di.scopes.UserScope
import com.chplalex.githubviewer.mvp.presenter.UserPresenter
import com.chplalex.githubviewer.ui.fragment.UserFragment
import dagger.Subcomponent

@UserScope
@Subcomponent(
    modules = [
        UserModule::class
    ]
)
interface UserSubcomponent {
    fun inject(userFragment: UserFragment)
    fun inject(userPresenter: UserPresenter)
}