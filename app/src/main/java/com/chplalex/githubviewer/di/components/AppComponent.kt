package com.chplalex.githubviewer.di.components

import com.chplalex.githubviewer.di.modules.*
import com.chplalex.githubviewer.mvp.presenter.MainPresenter
import com.chplalex.githubviewer.ui.activity.MainActivity
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        ApiModule::class,
        AppModule::class,
        DatabaseModule::class,
        ImageModule::class,
        NavigationModule::class
    ]
)

interface AppComponent {
    fun usersSubcomponent(): UsersSubcomponent
    fun inject(mainActivity: MainActivity)
    fun inject(mainPresenter: MainPresenter)
}