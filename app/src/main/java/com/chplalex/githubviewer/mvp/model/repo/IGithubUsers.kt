package com.chplalex.githubviewer.mvp.model.repo

import com.chplalex.githubviewer.mvp.model.entity.GithubUser
import io.reactivex.rxjava3.core.Single

interface IGithubUsers {
    fun getUsers(): Single<List<GithubUser>>
}