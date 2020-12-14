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
import com.chplalex.githubviewer.ui.App
import io.reactivex.rxjava3.core.Scheduler
import io.reactivex.rxjava3.disposables.Disposable
import moxy.MvpPresenter
import ru.terrakok.cicerone.Router
import javax.inject.Inject

class UsersPresenter() : MvpPresenter<UsersView>() {

    @Inject lateinit var users: IGithubUsers
    @Inject lateinit var scheduler: Scheduler
    @Inject lateinit var router: Router

    init {
        App.instance.appСomponent.inject(this)
    }

    class UsersListPresenter : IUsersListPresenter {

        override var itemClickListener: ((IUserItemView) -> Unit)? = null

        val usersList = mutableListOf<GithubUser>()

        override fun bindView(view: IUserItemView) {
            val user = usersList[view.pos]
            user.login?.let { view.setLogin(it) }
            user.avatarUrl?.let { view.loadAvatar(it) }
        }

        override fun getCount() = usersList.size
    }

    val usersListPresenter = UsersListPresenter()

    private var disposable: Disposable? = null

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        viewState.init()
        loadData()

        usersListPresenter.itemClickListener = { userItemView ->
            val user = usersListPresenter.usersList[userItemView.pos]
            router.navigateTo(Screens.UserScreen(user))
        }
    }

    private fun loadData() {
        disposable = users.getUsers()
            .observeOn(scheduler)
            .subscribe(
                {
                    usersListPresenter.usersList.clear()
                    usersListPresenter.usersList.addAll(it)
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