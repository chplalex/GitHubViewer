package com.chplalex.githubviewer.ui

import android.app.Application
import com.chplalex.githubviewer.di.AppComponent
import com.chplalex.githubviewer.di.DaggerAppComponent
import com.chplalex.githubviewer.di.modules.AppModule
import com.chplalex.githubviewer.mvp.model.entity.room.db.Database

class App : Application() {

    companion object {
        lateinit var instance: App
    }

    lateinit var appСomponent: AppComponent

    override fun onCreate() {
        super.onCreate()
        instance = this

        appСomponent = DaggerAppComponent.builder()
            .appModule(AppModule(this))
            .build()
    }
}