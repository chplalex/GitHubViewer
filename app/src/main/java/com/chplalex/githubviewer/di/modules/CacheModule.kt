package com.chplalex.githubviewer.di.modules

import androidx.room.Room
import com.chplalex.githubviewer.mvp.model.entity.room.db.Database
import com.chplalex.githubviewer.mvp.model.repo.CacheRepos
import com.chplalex.githubviewer.mvp.model.repo.CacheUsers
import com.chplalex.githubviewer.mvp.model.repo.ICacheRepos
import com.chplalex.githubviewer.mvp.model.repo.ICacheUsers
import com.chplalex.githubviewer.ui.App
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class CacheModule {
    @Singleton
    @Provides
    fun database(app: App): Database = Room.databaseBuilder(app, Database::class.java, Database.DB_NAME).build()

    @Singleton
    @Provides
    fun cacheUsers(database: Database): ICacheUsers = CacheUsers(database)

    @Singleton
    @Provides
    fun cacheRepos(database: Database): ICacheRepos = CacheRepos(database)
}