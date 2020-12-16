package com.chplalex.githubviewer.di.modules

import com.chplalex.githubviewer.di.scopes.UsersScope
import com.chplalex.githubviewer.mvp.model.api.IDataSource
import com.chplalex.githubviewer.mvp.model.entity.room.db.Database
import com.chplalex.githubviewer.mvp.model.network.INetworkStatus
import com.chplalex.githubviewer.mvp.model.repo.CacheUsers
import com.chplalex.githubviewer.mvp.model.repo.ICacheUsers
import com.chplalex.githubviewer.mvp.model.repo.IGithubUsers
import com.chplalex.githubviewer.mvp.model.repo.RetrofitGithubUsers
import dagger.Module
import dagger.Provides

@Module
class UsersModule {

    @UsersScope
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

    @UsersScope
    @Provides
    fun cacheUsers(database: Database): ICacheUsers = CacheUsers(database)

}