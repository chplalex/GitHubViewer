package com.chplalex.githubviewer.mvp.model.repo

import com.chplalex.githubviewer.mvp.model.entity.GithubRepo
import com.chplalex.githubviewer.mvp.model.entity.GithubUser
import com.chplalex.githubviewer.mvp.model.entity.room.db.Database
import com.chplalex.githubviewer.mvp.model.entity.room.entity.RoomGithubRepo
import io.reactivex.rxjava3.core.Single

class CacheRepos(private val db: Database) : ICacheRepos {

    override fun putTo(user: GithubUser, repos: List<GithubRepo>) {
        val roomUser = user.login?.let { db.userDao.findByLogin(it) } ?: throw RuntimeException("No such user in cash")
        val roomRepos = repos.map {
            RoomGithubRepo(
                it.id ?: "",
                it.name ?: "",
                it.forksCount ?: 0,
                roomUser.id
            )
        }
        db.repoDao.insert(roomRepos)
    }

    override fun getFrom(user: GithubUser) = Single.fromCallable {
        val roomUser = user.login?.let { db.userDao.findByLogin(it) }
            ?: throw RuntimeException("No such user in cash")
        db.repoDao.findForUser(roomUser.id).map {
            GithubRepo(
                it.id,
                it.name,
                "",
                "",
                "",
                "",
                it.forksCount,
                it.userId
            )
        }
    }

}