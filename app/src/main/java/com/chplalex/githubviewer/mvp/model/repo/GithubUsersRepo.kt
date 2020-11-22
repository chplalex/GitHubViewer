package com.chplalex.githubviewer.mvp.model.repo

import com.chplalex.githubviewer.mvp.model.entity.GithubUser

class GithubUsersRepo {

    private val repositories = listOf(
        GithubUser("login1"),
        GithubUser("login2"),
        GithubUser("login3"),
        GithubUser("login4"),
        GithubUser("login5"),
        GithubUser("login6")
    )

    fun getUsers(): List<GithubUser> = repositories

    fun getUser(login: String): GithubUser {
        // должен быть запрос в репозиторий по логину и возврат значения или exception
        return GithubUser(login)
    }
}