package com.chplalex.githubviewer.mvp.model.entity.room.db

import androidx.room.RoomDatabase
import com.chplalex.githubviewer.mvp.model.entity.room.entity.RoomGithubRepo
import com.chplalex.githubviewer.mvp.model.entity.room.entity.RoomGithubUser
import com.chplalex.githubviewer.mvp.model.entity.room.dao.RepositoryDao
import com.chplalex.githubviewer.mvp.model.entity.room.dao.UserDao

@androidx.room.Database(entities = [RoomGithubUser::class, RoomGithubRepo::class], version = 1)
abstract class Database : RoomDatabase() {
    abstract val userDao: UserDao
    abstract val repoDao: RepositoryDao

    companion object {
        const val DB_NAME = "githubviewer.db"
    }
}