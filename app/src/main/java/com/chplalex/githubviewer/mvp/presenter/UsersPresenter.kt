package com.chplalex.githubviewer.mvp.presenter

import com.chplalex.githubviewer.mvp.model.entity.GithubUser
import com.chplalex.githubviewer.mvp.model.repo.GithubUsersRepo
import com.chplalex.githubviewer.mvp.presenter.list.IUsersListPresenter
import com.chplalex.githubviewer.mvp.view.UsersView
import com.chplalex.githubviewer.mvp.view.list.UserItemView
import com.chplalex.githubviewer.navigation.Screens
import moxy.MvpPresenter
import ru.terrakok.cicerone.Router

class UsersPresenter(
    private val router: Router,
    private val usersRepo: GithubUsersRepo
) : MvpPresenter<UsersView>() {

    class UsersListPresenter : IUsersListPresenter {

        override var itemClickListener: ((UserItemView) -> Unit)? = null

        val users = mutableListOf<GithubUser>()

        override fun bindView(view: UserItemView) {
            val user = users[view.pos]
            view.setLogin(user.login)
        }

        override fun getCount() = users.size
    }

    val usersListPresenter = UsersListPresenter()

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        viewState.init()
        loadData()

        usersListPresenter.itemClickListener = {userItemView ->
            val user = usersListPresenter.users[userItemView.pos]
            router.navigateTo(Screens.UserScreen(user))
        }
    }

    private fun loadData() {
        val users = usersRepo.getUsers()
        usersListPresenter.users.clear()
        usersListPresenter.users.addAll(users)
        viewState.updateUsersList()
    }

    fun backClick(): Boolean {
        router.exit()
        return true
    }
}