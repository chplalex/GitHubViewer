package com.chplalex.githubviewer.mvp.model.repo

import com.chplalex.githubviewer.mvp.model.api.IDataSource
import com.chplalex.githubviewer.mvp.model.entity.GithubUser
import com.chplalex.githubviewer.mvp.model.entity.GithubUserRepo
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers

class RetrofitGithubUsersRepo(private val dataSource: IDataSource): IGithubUsersRepo {
    override fun getUsers(): Single<List<GithubUser>> = dataSource.loadUsers().subscribeOn(Schedulers.io())
    override fun getUser(login: String): Single<GithubUser> = dataSource.loadUser(login).subscribeOn(Schedulers.io())
    override fun getUserReposByUrl(url: String): Single<List<GithubUserRepo>> = dataSource.loadReposByUrl(url).subscribeOn(Schedulers.io())
    override fun getUserReposByLogin(login: String): Single<List<GithubUserRepo>> = dataSource.loadReposByLogin(login).subscribeOn(Schedulers.io())
}