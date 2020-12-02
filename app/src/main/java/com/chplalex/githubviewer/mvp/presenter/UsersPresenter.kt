package com.chplalex.githubviewer.mvp.presenter

import android.util.Log
import com.chplalex.githubviewer.TAG
import com.chplalex.githubviewer.mvp.model.entity.GithubUser
import com.chplalex.githubviewer.mvp.model.repo.GithubUsersRepo
import com.chplalex.githubviewer.mvp.model.repo.IGithubUsersRepo
import com.chplalex.githubviewer.mvp.presenter.list.IUsersListPresenter
import com.chplalex.githubviewer.mvp.view.UsersView
import com.chplalex.githubviewer.mvp.view.list.UserItemView
import com.chplalex.githubviewer.navigation.Screens
import io.reactivex.rxjava3.core.Scheduler
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.disposables.Disposable
import io.reactivex.rxjava3.kotlin.addTo
import moxy.MvpPresenter
import ru.terrakok.cicerone.Router

class UsersPresenter(
    private val router: Router,
    private val usersRepo: IGithubUsersRepo,
    private val scheduler: Scheduler
) : MvpPresenter<UsersView>() {

    class UsersListPresenter : IUsersListPresenter {

        override var itemClickListener: ((UserItemView) -> Unit)? = null

        val users = mutableListOf<GithubUser>()

        override fun bindView(view: UserItemView) {
            val user = users[view.pos]
            user.login?.let { view.setLogin(it) }
            user.avatarUrl?.let { view.loadAvatar(it) }
        }

        override fun getCount() = users.size
    }

    val usersListPresenter = UsersListPresenter()
    private var disposable: Disposable? = null

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        viewState.init()
        loadData()

        usersListPresenter.itemClickListener = { userItemView ->
            val user = usersListPresenter.users[userItemView.pos]
            router.navigateTo(Screens.UserScreen(user))
        }
    }

    private fun loadData() {
        disposable = usersRepo.getUsers()
            .observeOn(scheduler)
            .subscribe(
                {
                    usersListPresenter.users.clear()
                    usersListPresenter.users.addAll(it)
                    viewState.updateUsersList()
                },
                {
                    Log.e(TAG, "loadData() -> error = $it")
                })
    }

    fun backClick(): Boolean {
        router.exit()
        return true
    }

    override fun onDestroy() {
        super.onDestroy()
        disposable?.dispose()
    }
}