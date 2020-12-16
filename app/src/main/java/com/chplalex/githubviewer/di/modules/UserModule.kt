package com.chplalex.githubviewer.di.modules

import com.chplalex.githubviewer.di.scopes.UserScope
import com.chplalex.githubviewer.mvp.model.api.IDataSource
import com.chplalex.githubviewer.mvp.model.entity.room.db.Database
import com.chplalex.githubviewer.mvp.model.network.INetworkStatus
import com.chplalex.githubviewer.mvp.model.repo.CacheRepos
import com.chplalex.githubviewer.mvp.model.repo.ICacheRepos
import com.chplalex.githubviewer.mvp.model.repo.IGithubRepos
import com.chplalex.githubviewer.mvp.model.repo.RetrofitGithubRepos
import dagger.Module
import dagger.Provides

@Module
class UserModule {

    @UserScope
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

    @UserScope
    @Provides
    fun cacheRepos(database: Database): ICacheRepos = CacheRepos(database)

}