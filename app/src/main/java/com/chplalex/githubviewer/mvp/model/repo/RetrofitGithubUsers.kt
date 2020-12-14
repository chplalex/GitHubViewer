package com.chplalex.githubviewer.mvp.model.repo

import com.chplalex.githubviewer.mvp.model.api.IDataSource
import com.chplalex.githubviewer.mvp.model.entity.GithubUser
import com.chplalex.githubviewer.mvp.model.network.INetworkStatus
import com.chplalex.githubviewer.ui.App
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers
import javax.inject.Inject

class RetrofitGithubUsers(
    private val dataSource: IDataSource,
    private val networkStatus: INetworkStatus,
    private val cache: ICacheUsers
) : IGithubUsers {

    override fun getUsers(): Single<List<GithubUser>> =
        networkStatus.isOnlineSingle().flatMap { isOnline ->
            if (isOnline)
                dataSource.loadUsers().doAfterSuccess { cache.putTo(it) }
            else
                cache.getFrom()
        }.subscribeOn(Schedulers.io())

}