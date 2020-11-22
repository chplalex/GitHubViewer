package com.chplalex.githubviewer.navigation

import com.chplalex.githubviewer.mvp.model.entity.GithubUser
import com.chplalex.githubviewer.ui.fragment.UserFragment
import com.chplalex.githubviewer.ui.fragment.UsersFragment
import ru.terrakok.cicerone.android.support.SupportAppScreen

class Screens {
    class UsersScreen : SupportAppScreen() {
        override fun getFragment() = UsersFragment.newInstance()
    }

    class UserScreen(private val user: GithubUser) : SupportAppScreen() {
        override fun getFragment() = UserFragment.newInstance(user)
    }
}