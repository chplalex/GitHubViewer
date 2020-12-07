package com.chplalex.githubviewer.mvp.model.repo

import com.chplalex.githubviewer.mvp.model.entity.GithubUser
import io.reactivex.rxjava3.core.Single

interface ICacheUsers {
    fun putTo(users: List<GithubUser>)
    fun getFrom() : Single<List<GithubUser>>
}