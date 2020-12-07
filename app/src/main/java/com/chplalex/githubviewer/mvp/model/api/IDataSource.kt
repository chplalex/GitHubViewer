package com.chplalex.githubviewer.mvp.model.api

import com.chplalex.githubviewer.mvp.model.entity.GithubUser
import com.chplalex.githubviewer.mvp.model.entity.GithubRepo
import io.reactivex.rxjava3.core.Single
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Url

interface IDataSource {
    @GET("users")
    fun loadUsers(): Single<List<GithubUser>>

    @GET
    fun loadRepos(@Url url: String): Single<List<GithubRepo>>
}