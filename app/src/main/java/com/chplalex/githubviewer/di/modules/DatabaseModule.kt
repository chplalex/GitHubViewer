package com.chplalex.githubviewer.di.modules

import androidx.room.Room
import com.chplalex.githubviewer.mvp.model.entity.room.db.Database
import com.chplalex.githubviewer.ui.App
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class DatabaseModule {
    @Singleton
    @Provides
    fun database(app: App): Database = Room.databaseBuilder(app, Database::class.java, Database.DB_NAME).build()
}