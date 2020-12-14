package com.chplalex.githubviewer.di

import com.chplalex.githubviewer.di.modules.*
import com.chplalex.githubviewer.mvp.presenter.MainPresenter
import com.chplalex.githubviewer.mvp.presenter.UserPresenter
import com.chplalex.githubviewer.mvp.presenter.UsersPresenter
import com.chplalex.githubviewer.ui.activity.MainActivity
import com.chplalex.githubviewer.ui.adapter.UsersRvAdapter
import com.chplalex.githubviewer.ui.fragment.UserFragment
import com.chplalex.githubviewer.ui.fragment.UsersFragment
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        ApiModule::class,
        AppModule::class,
        CacheModule::class,
        NavigationModule::class,
        RepoModule::class
    ]
)

interface AppComponent {
    fun inject(mainActivity: MainActivity)
    fun inject(mainPresenter: MainPresenter)
    fun inject(usersPresenter: UsersPresenter)
    fun inject(userPresenter: UserPresenter)
    fun inject(viewHolder: UsersRvAdapter.ViewHolder)
    fun inject(userFragment: UserFragment)
}