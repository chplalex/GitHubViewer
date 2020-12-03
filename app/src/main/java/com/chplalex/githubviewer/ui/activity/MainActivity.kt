package com.chplalex.githubviewer.ui.activity

import android.os.Bundle
import android.util.Log
import com.chplalex.githubviewer.ui.App
import com.chplalex.githubviewer.ui.BackButtonListener
import com.chplalex.githubviewer.R
import com.chplalex.githubviewer.TAG
import com.chplalex.githubviewer.mvp.presenter.MainPresenter
import com.chplalex.githubviewer.mvp.view.MainView
import moxy.MvpAppCompatActivity
import moxy.ktx.moxyPresenter
import ru.terrakok.cicerone.android.support.SupportAppNavigator

class MainActivity : MvpAppCompatActivity(), MainView {

    val navigatorHolder = App.instance.navigatorHolder
    val navigator = SupportAppNavigator(this, supportFragmentManager, R.id.container)

    val presenter by moxyPresenter {
        MainPresenter(App.instance.router)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // rx leaning
        //Creation().exec()
        //Operators().exec()
    }

    override fun onResumeFragments() {
        super.onResumeFragments()
        navigatorHolder.setNavigator(navigator)
    }

    override fun onPause() {
        super.onPause()
        navigatorHolder.removeNavigator()
    }

    override fun onBackPressed() {
        supportFragmentManager.fragments.forEach {
            if (it is BackButtonListener) {
                it.backPressed()
                return
            }
        }
        presenter.backClick()
    }
}