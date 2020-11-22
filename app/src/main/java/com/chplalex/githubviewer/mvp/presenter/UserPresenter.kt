package com.chplalex.githubviewer.mvp.presenter

import com.chplalex.githubviewer.mvp.model.entity.GithubUser
import com.chplalex.githubviewer.mvp.model.repo.GithubUsersRepo
import com.chplalex.githubviewer.mvp.view.UserView
import moxy.MvpPresenter
import ru.terrakok.cicerone.Router

class UserPresenter(
    private val router: Router,
    private val user: GithubUser
) : MvpPresenter<UserView>() {

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        viewState.update(user.login)
    }

    fun backClick(): Boolean {
        router.exit()
        return true
    }

}