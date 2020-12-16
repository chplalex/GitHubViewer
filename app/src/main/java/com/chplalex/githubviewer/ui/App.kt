package com.chplalex.githubviewer.ui

import android.app.Application
import com.chplalex.githubviewer.di.components.AppComponent
import com.chplalex.githubviewer.di.components.DaggerAppComponent
import com.chplalex.githubviewer.di.components.UserSubcomponent
import com.chplalex.githubviewer.di.components.UsersSubcomponent
import com.chplalex.githubviewer.di.modules.AppModule

class App : Application() {

    companion object {
        lateinit var instance: App
    }

    lateinit var appСomponent: AppComponent
        private set
    var usersSubcomponent: UsersSubcomponent? = null
        private set
    var userSubcomponent: UserSubcomponent? = null
        private set

    override fun onCreate() {
        super.onCreate()
        instance = this

        appСomponent = DaggerAppComponent.builder()
            .appModule(AppModule(this))
            .build()
    }

    fun createUsersSubcomponent() = appСomponent.usersSubcomponent().also {
        usersSubcomponent = it
    }

    fun destroyUsersSubcomponent() {
        usersSubcomponent = null
    }

    fun createUserSubcomponent() = usersSubcomponent?.reposSubcomponent().also {
        userSubcomponent = it
    }

    fun destroyUserSubcomponent() {
        userSubcomponent = null
    }
}