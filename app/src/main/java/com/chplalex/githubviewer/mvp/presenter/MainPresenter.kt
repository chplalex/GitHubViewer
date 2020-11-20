package com.chplalex.githubviewer.mvp.presenter

import com.chplalex.githubviewer.mvp.view.MainView
import com.chplalex.githubviewer.navigation.Screens
import moxy.MvpPresenter
import ru.terrakok.cicerone.Router

class MainPresenter(val router: Router) : MvpPresenter<MainView>() {

    /**
     *
     * Callback after the first presenter init and view binding. If this
     * presenter instance will have to attach more views in the future, this method
     * will not be called.
     *
     * There you can interact with a [.viewState].
     */
    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        router.replaceScreen(Screens.UsersScreen())
    }

    fun backClick() {
        router.exit()
    }

}