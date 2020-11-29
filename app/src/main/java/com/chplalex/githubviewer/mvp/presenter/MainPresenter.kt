package com.chplalex.githubviewer.mvp.presenter

import com.chplalex.githubviewer.mvp.view.MainView
import com.chplalex.githubviewer.navigation.Screens
import moxy.MvpPresenter
import ru.terrakok.cicerone.Router

class MainPresenter(val router: Router) : MvpPresenter<MainView>() {

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        router.replaceScreen(Screens.UsersScreen())
    }

    fun backClick() {
        router.exit()
    }

}