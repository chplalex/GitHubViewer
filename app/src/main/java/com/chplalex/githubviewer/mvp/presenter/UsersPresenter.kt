package com.chplalex.githubviewer.mvp.presenter

import android.os.Handler
import android.os.Looper
import android.util.Log
import com.chplalex.githubviewer.TAG
import com.chplalex.githubviewer.mvp.model.entity.GithubUser
import com.chplalex.githubviewer.mvp.model.repo.IGithubUsers
import com.chplalex.githubviewer.mvp.presenter.list.IUsersListPresenter
import com.chplalex.githubviewer.mvp.view.UsersView
import com.chplalex.githubviewer.mvp.view.list.IUserItemView
import com.chplalex.githubviewer.navigation.Screens
import io.reactivex.rxjava3.core.Scheduler
import io.reactivex.rxjava3.disposables.Disposable
import moxy.MvpPresenter
import ru.terrakok.cicerone.Router

class UsersPresenter(
    private val router: Router,
    private val usersRepo: IGithubUsers,
    private val scheduler: Scheduler
) : MvpPresenter<UsersView>() {

    class UsersListPresenter : IUsersListPresenter {

        override var itemClickListener: ((IUserItemView) -> Unit)? = null

        val users = mutableListOf<GithubUser>()

        override fun bindView(view: IUserItemView) {
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

    private var flagExit = false

    fun backClick(): Boolean {
        router.exit()
        return true

        Log.d(TAG, "presenter.backClick(), flag = $flagExit")
        if (flagExit) {
            router.exit()
            return true
        } else {
            flagExit = true
            Handler(Looper.getMainLooper()).postDelayed(kotlinx.coroutines.Runnable {
                Log.d(TAG, "presenter.backClick() -> runnable -> flagExit -> false ")
                flagExit = false
                                                                                    }, 2000)
            viewState.showExitMessage()
            return false
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        disposable?.dispose()
    }
}