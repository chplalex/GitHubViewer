package com.chplalex.githubviewer.mvp.model.repo

import com.chplalex.githubviewer.mvp.model.entity.GithubUser
import com.google.gson.FieldNamingPolicy
import com.google.gson.GsonBuilder
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create

class GithubUsersRepo {

    private val users = listOf(
        GithubUser("login1"),
        GithubUser("login2"),
        GithubUser("login3"),
        GithubUser("login4"),
        GithubUser("login5"),
        GithubUser("login6")
    )

//    Версия до rx
//
//    fun getUsers(): List<GithubUser> = repositories
//
//    fun getUser(login: String) = GithubUser(login)
//    }

    // версия rx до retrofit
    fun getUsers(): Observable<List<GithubUser>> = Observable.fromCallable() { users }
        .subscribeOn(Schedulers.io())

}