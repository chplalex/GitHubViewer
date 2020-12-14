package com.chplalex.githubviewer.di.modules

import com.chplalex.githubviewer.mvp.model.api.IDataSource
import com.chplalex.githubviewer.mvp.model.network.INetworkStatus
import com.chplalex.githubviewer.mvp.model.repo.*
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class RepoModule {

    @Singleton
    @Provides
    fun users(
        dataSource: IDataSource,
        networkStatus: INetworkStatus,
        cacheUsers: ICacheUsers
    ): IGithubUsers = RetrofitGithubUsers(
        dataSource,
        networkStatus,
        cacheUsers
    )

    @Singleton
    @Provides
    fun repos(
        dataSource: IDataSource,
        networkStatus: INetworkStatus,
        cacheRepos: ICacheRepos
    ): IGithubRepos = RetrofitGithubRepos(
        dataSource,
        networkStatus,
        cacheRepos
    )

}