package com.chplalex.githubviewer.mvp.model.repo

import com.chplalex.githubviewer.mvp.model.entity.GithubRepo
import com.chplalex.githubviewer.mvp.model.entity.GithubUser
import io.reactivex.rxjava3.core.Single

interface ICacheRepos {
    fun putTo(user: GithubUser, repos: List<GithubRepo>)
    fun getFrom(user: GithubUser) : Single<List<GithubRepo>>
}