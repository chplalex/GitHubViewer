package com.chplalex.githubviewer.mvp.model.repo

import com.chplalex.githubviewer.mvp.model.entity.GithubUser
import com.chplalex.githubviewer.mvp.model.entity.GithubUserRepo
import io.reactivex.rxjava3.core.Single

interface IGithubUsersRepo {
    fun getUsers(): Single<List<GithubUser>>
    fun getUser(login: String): Single<GithubUser>
    fun getUserReposByUrl(url: String): Single<List<GithubUserRepo>>
    fun getUserReposByLogin(login: String): Single<List<GithubUserRepo>>
}