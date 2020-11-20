package com.chplalex.githubviewer.ui.fragment

import com.chplalex.githubviewer.App
import com.chplalex.githubviewer.BackButtonListener
import com.chplalex.githubviewer.mvp.model.repo.GithubUsersRepo
import com.chplalex.githubviewer.mvp.presenter.UsersPresenter
import com.chplalex.githubviewer.mvp.view.UsersView
import moxy.MvpAppCompatFragment
import moxy.ktx.moxyPresenter

class UsersFragment : MvpAppCompatFragment(), UsersView, BackButtonListener {

    companion object {
        fun newInstance() = UsersFragment()
    }

    val presenter by moxyPresenter {
        UsersPresenter(App.instance.router, GithubUsersRepo())
    }

    override fun init() {
        TODO("Not yet implemented")
    }

    override fun updateUsersList() {
        TODO("Not yet implemented")
    }

    override fun backPressed(): Boolean {
        TODO("Not yet implemented")
    }

}