package com.chplalex.githubviewer.navigation

import ru.terrakok.cicerone.android.support.SupportAppScreen

class Screens {
    class UsesScreen : SupportAppScreen() {
        override fun getFragment() = UsersFragment.newinstance()
    }
}