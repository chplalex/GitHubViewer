package com.chplalex.githubviewer.mvp.model.repo

import com.chplalex.githubviewer.mvp.model.entity.GithubRepo
import com.chplalex.githubviewer.mvp.model.entity.GithubUser
import io.reactivex.rxjava3.core.Single

interface IGithubRepos {
    fun getRepos(user: GithubUser): Single<List<GithubRepo>>
}