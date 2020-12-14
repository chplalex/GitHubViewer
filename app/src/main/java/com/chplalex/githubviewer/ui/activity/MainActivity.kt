package com.chplalex.githubviewer.ui.activity

import android.os.Bundle
import com.chplalex.githubviewer.ui.App
import com.chplalex.githubviewer.ui.BackButtonListener
import com.chplalex.githubviewer.R
import com.chplalex.githubviewer.mvp.presenter.MainPresenter
import com.chplalex.githubviewer.mvp.view.MainView
import moxy.MvpAppCompatActivity
import moxy.ktx.moxyPresenter
import ru.terrakok.cicerone.NavigatorHolder
import ru.terrakok.cicerone.android.support.SupportAppNavigator
import javax.inject.Inject

class MainActivity : MvpAppCompatActivity(), MainView {

    @Inject lateinit var navigatorHolder: NavigatorHolder

    private val navigator = SupportAppNavigator(this, supportFragmentManager, R.id.container)

    val presenter by moxyPresenter {
        MainPresenter().apply {
            App.instance.appСomponent.inject(this)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        App.instance.appСomponent.inject(this)

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