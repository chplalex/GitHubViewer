package com.chplalex.githubviewer.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.chplalex.githubviewer.R
import com.chplalex.githubviewer.mvp.model.entity.GithubUser
import com.chplalex.githubviewer.mvp.model.repo.GithubUsersRepo
import com.chplalex.githubviewer.mvp.presenter.UserPresenter
import com.chplalex.githubviewer.mvp.view.UserView
import com.chplalex.githubviewer.ui.App
import com.chplalex.githubviewer.ui.BackButtonListener
import kotlinx.android.synthetic.main.fragment_user.*
import moxy.MvpAppCompatFragment
import moxy.ktx.moxyPresenter

class UserFragment() : MvpAppCompatFragment(), UserView, BackButtonListener {

    companion object {
        private const val KEY = "USER_FRAGMENT_AGR_KEY"

        fun newInstance(user: GithubUser) = UserFragment().apply {
            arguments = Bundle().apply {
                putParcelable(KEY, user)
            }
        }
    }

    private val presenter by moxyPresenter {
        val user = arguments?.getParcelable<GithubUser>(KEY)
        UserPresenter(App.instance.router, user!!)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = View.inflate(context, R.layout.fragment_user, null).apply {
        activity?.title = context.getString(R.string.fragment_title_user)
    }

    override fun update(login: String) {
        textLogin.text = login
    }

    override fun backPressed() = presenter.backClick()

}