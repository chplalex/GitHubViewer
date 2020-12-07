package com.chplalex.githubviewer.mvp.model.repo

import com.chplalex.githubviewer.mvp.model.entity.GithubUser
import com.chplalex.githubviewer.mvp.model.entity.room.db.Database
import com.chplalex.githubviewer.mvp.model.entity.room.entity.RoomGithubUser
import io.reactivex.rxjava3.core.Single

class CacheUsers(private val db: Database) : ICacheUsers {

    override fun putTo(users: List<GithubUser>) {
        val roomUsers = users.map { user ->
            RoomGithubUser(
                user.id ?: "",
                user.login ?: "",
                user.avatarUrl ?: "",
                user.reposUrl ?: ""
            )
        }
        db.userDao.insert(roomUsers)
    }

    override fun getFrom() = Single.fromCallable {
        db.userDao.getAll().map { roomUser ->
            GithubUser(
                roomUser.id,
                roomUser.login,
                roomUser.avatarUrl,
                roomUser.reposUrl
            )
        }
    }

}