package com.chplalex.githubviewer.mvp.model.repo

import com.chplalex.githubviewer.mvp.model.entity.GithubUser
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.schedulers.Schedulers

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


    fun getUsers(): Observable<List<GithubUser>> = Observable.fromCallable() { users }
        .subscribeOn(Schedulers.io())

}