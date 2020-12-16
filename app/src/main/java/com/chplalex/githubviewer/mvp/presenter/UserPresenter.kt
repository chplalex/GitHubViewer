package com.chplalex.githubviewer.mvp.presenter

import android.util.Log
import com.chplalex.githubviewer.TAG
import com.chplalex.githubviewer.mvp.model.entity.GithubUser
import com.chplalex.githubviewer.mvp.model.entity.GithubRepo
import com.chplalex.githubviewer.mvp.model.repo.IGithubRepos
import com.chplalex.githubviewer.mvp.presenter.list.IUserReposListPresenter
import com.chplalex.githubviewer.mvp.view.UserView
import com.chplalex.githubviewer.mvp.view.list.IUserReposItemView
import com.chplalex.githubviewer.ui.App
import io.reactivex.rxjava3.core.Scheduler
import io.reactivex.rxjava3.disposables.Disposable
import moxy.MvpPresenter
import ru.terrakok.cicerone.Router
import javax.inject.Inject

class UserPresenter(private val user: GithubUser) : MvpPresenter<UserView>() {

    @Inject lateinit var repos: IGithubRepos
    @Inject lateinit var scheduler: Scheduler
    @Inject lateinit var router: Router

    init {
        App.instance.userSubcomponent?.inject(this)
    }

    class UserReposListPresenter : IUserReposListPresenter {

        val repos = mutableListOf<GithubRepo>()

        override var itemClickListener: ((IUserReposItemView) -> Unit)? = null

        override fun bindView(view: IUserReposItemView) {
            val repo = repos[view.pos]
            view.setName(repo.name)
            view.setForksCount(repo.forksCount)
        }

        override fun getCount() = repos.size
    }

    private var disposable: Disposable? = null

    val userReposListPresenter = UserReposListPresenter()

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        viewState.init()
        user.login?.let { viewState.setLogin(it) }
        user.avatarUrl?.let { viewState.setAvatar(it) }
        loadRepos()
    }

    private fun loadRepos() {
        //TODO разобраться с нуллабельностью полей в user
        disposable = repos.getRepos(user)
            .observeOn(scheduler)
            .subscribe(
                {
                    userReposListPresenter.repos.clear()
                    userReposListPresenter.repos.addAll(it)
                    viewState.updateReposList()
                    Log.d(TAG, "loadRepos() -> success. list.size = ${it.size}")
                },
                {
                    Log.e(TAG, "loadRepos() -> error = $it")
                })
    }

    fun backClick(): Boolean {
        router.exit()
        return true
    }

    override fun onDestroy() {
        super.onDestroy()
        disposable?.dispose()
        viewState.destroy()
    }

}