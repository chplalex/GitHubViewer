package com.chplalex.githubviewer.mvp.model.repo

import com.chplalex.githubviewer.mvp.model.api.IDataSource
import com.chplalex.githubviewer.mvp.model.entity.GithubRepo
import com.chplalex.githubviewer.mvp.model.entity.GithubUser
import com.chplalex.githubviewer.mvp.model.network.INetworkStatus
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers

class RetrofitGithubRepos(
    private val dataSource: IDataSource,
    private val networkStatus: INetworkStatus,
    private val cache: ICacheRepos,
) : IGithubRepos {

    override fun getRepos(user: GithubUser) = networkStatus.isOnlineSingle().flatMap { isOnline ->
        if (isOnline)
            user.reposUrl?.let { url ->
                dataSource.loadRepos(url).doAfterSuccess { cache.putTo(user, it) }
            } ?: Single.error<List<GithubRepo>>(RuntimeException("User has no repos url"))
        else
            cache.getFrom(user)
    }.subscribeOn(Schedulers.io())
}