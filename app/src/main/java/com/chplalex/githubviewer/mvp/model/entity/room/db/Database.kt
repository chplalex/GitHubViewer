package com.chplalex.githubviewer.mvp.model.entity.room.db

import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase
import com.chplalex.githubviewer.mvp.model.entity.room.entity.RoomGithubRepo
import com.chplalex.githubviewer.mvp.model.entity.room.entity.RoomGithubUser
import com.chplalex.githubviewer.mvp.model.entity.room.dao.RepositoryDao
import com.chplalex.githubviewer.mvp.model.entity.room.dao.UserDao
import java.lang.RuntimeException

@androidx.room.Database(entities = [RoomGithubUser::class, RoomGithubRepo::class], version = 1)
abstract class Database : RoomDatabase() {
    abstract val userDao: UserDao
    abstract val repoDao: RepositoryDao

    companion object {
        private const val DB_NAME = "githubviewer.db"
        private var instance: Database? = null

        fun getInstance() = instance ?: throw RuntimeException("Database has not been created. Please, call create(context)")

        fun create(context: Context) {
            if (instance == null) {
                instance = Room.databaseBuilder(context, Database::class.java, DB_NAME).build()
            }
        }
    }
}