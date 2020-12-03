package com.chplalex.githubviewer.mvp.model.api

import com.chplalex.githubviewer.mvp.model.entity.GithubUser
import com.chplalex.githubviewer.mvp.model.entity.GithubUserRepo
import io.reactivex.rxjava3.core.Single
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Url

interface IDataSource {
    @GET("users")
    fun loadUsers(): Single<List<GithubUser>>

    @GET("users/{login}")
    fun loadUser(@Path("login") login: String): Single<GithubUser>

    @GET("users/{login}/repos")
    fun loadReposByLogin(@Path("login") login: String): Single<List<GithubUserRepo>>

    @GET
    fun loadReposByUrl(@Url url: String): Single<List<GithubUserRepo>>
}